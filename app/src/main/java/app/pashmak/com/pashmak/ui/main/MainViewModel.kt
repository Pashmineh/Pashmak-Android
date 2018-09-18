package app.pashmak.com.pashmak.ui.main

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.home.Event
import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.data.source.preference.AppPreferencesHelper
import app.pashmak.com.pashmak.domain.home.CheckInUseCase
import app.pashmak.com.pashmak.domain.home.HomeDataUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.*
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import javax.inject.Inject
import app.pashmak.com.pashmak.util.livedata.Event as EventLiveData
import app.pashmak.com.pashmak.util.beacon.PashmakBeaconUtil


class MainViewModel
@Inject constructor(
        private val homeDataUseCase: HomeDataUseCase,
        private val preferencesHelper: AppPreferencesHelper,
        private val checkInUseCase: CheckInUseCase,
        private val resourceProvider: BaseResourceProvider,
        val pashmakBeaconUtil: PashmakBeaconUtil,
        val permissionUtil: PermissionUtil,
        val viewState: MainViewState
) : BaseViewModel() {

    companion object {

        const val REQUEST_CHECK_LOCATION_SETTINGS = 232

        private val GPS_PERMISSION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    }


    val eventListLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    val settingsLiveData: MutableLiveData<EventLiveData<Boolean>> = MutableLiveData()
    val messageLiveData: MutableLiveData<String> = MutableLiveData()


    init {
        //TODO do something for loading
        viewState.initialize(
                "${preferencesHelper.firstName} ${preferencesHelper.lastName}",
                getAvatarUrl(preferencesHelper.userPhone)
        )
        getHomeData()
        checkTodayCheckIn()
    }

    private fun checkTodayCheckIn() {
        if (preferencesHelper.latestCheckIn != 0L && DateUtils.isToday(preferencesHelper.latestCheckIn))
            viewState.todayCheckInEnable.value = false
    }

    fun getHomeData() = homeDataUseCase.execute(compositeDisposable, this::onHomeDataResponse)

    fun getFormattedEventDate(position: Int): String {
        val item = eventListLiveData.value?.get(position)
        return item?.let {
            resourceProvider.getString(R.string.event_formatted_date, it.getDayOfWeek(), it.getDayOfMonth(), it.getMonthName(), it.getYear(), it.getHour())
        } ?: ""
    }

    fun prepareCheckIn() {
        activityAction { permissionUtil.request(it, arrayOf(GPS_PERMISSION), this::onPermissionResponse) }
    }

    fun checkBeaconState() {
        activityAction{ pashmakBeaconUtil.checkMyBeacon(it, this::onBeaconFound, this::onFindingBeaconFailed) }
        viewState.isLoading.value = true
    }

    fun checkIn() {
        checkInUseCase.setParameters(CheckInType.MANUAL).execute(compositeDisposable, this::onCheckInResponse)
    }

    private fun onBeaconFound(){
        checkIn()
    }

    private fun onFindingBeaconFailed(hasSettingsIssue: Boolean){

        viewState.isLoading.value = false

        if(!hasSettingsIssue){
            messageLiveData.value = "Pasho Boro Sherkat"
        }
    }

    private fun onHomeDataResponse(response: APIResponse<HomeData>) {
        when (response) {
            is SuccessResponse -> {
                viewState.setHomeDataValues(response.value)
                eventListLiveData.value = response.value.eventList
            }
            is ErrorResponse -> {
            }
        }
    }

    private fun onCheckInResponse(response: APIResponse<CheckInResponse>) {
        viewState.isLoading.value = false
        when (response) {
            is SuccessResponse -> {
                preferencesHelper.latestCheckIn = response.value.timeEpoch
                viewState.onSuccessfulCheckIn()
            }
            is ErrorResponse -> {
                Log.d("CheckIn Response", "Failure")
            }
        }
    }

    private fun onPermissionResponse(grantedPermissions: List<String>, deniedPermissions: List<String>) {
        if (grantedPermissions.contains(GPS_PERMISSION)) {
            settingsLiveData.value = EventLiveData(true)
        }
    }
}