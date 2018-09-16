package app.pashmak.com.pashmak.ui.main

import android.graphics.drawable.Drawable
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.home.Event
import app.pashmak.com.pashmak.util.livedata.Event as EventLiveData
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
import app.pashmak.com.pashmak.util.PermissionUtil
import app.pashmak.com.pashmak.util.formatNumber
import app.pashmak.com.pashmak.util.getAvatarUrl
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import java.util.jar.Manifest
import javax.inject.Inject

class MainViewModel
@Inject constructor(
        private val homeDataUseCase: HomeDataUseCase,
        private val preferencesHelper: AppPreferencesHelper,
        private val checkInUseCase: CheckInUseCase,
        private val resourceProvider: BaseResourceProvider,
        val permissionUtil: PermissionUtil
) : BaseViewModel() {

    companion object {
        private val GPS_PERMISSION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    }

    val stateColor: NonNullLiveData<Int> = NonNullLiveData(resourceProvider.getColor(R.color.Kelly_green))
    val placeHolder: Drawable = resourceProvider.getDrawable(R.drawable.vector_person_48dp)!!
    val fullName: String = "${preferencesHelper.firstName} ${preferencesHelper.lastName}"
    val avatar: String = getAvatarUrl(preferencesHelper.userPhone)

    var balance: NonNullLiveData<String> = NonNullLiveData("0")
    var paid: NonNullLiveData<String> = NonNullLiveData("0")
    var cycle: NonNullLiveData<String> = NonNullLiveData("")

    val todayCheckInEnable: NonNullLiveData<Boolean> = NonNullLiveData(true)
    val checkInButtonText: NonNullLiveData<String> = NonNullLiveData("")
    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)

    val eventListLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    val settingsLiveData: MutableLiveData<EventLiveData<Boolean>> = MutableLiveData()
    val checkInLiveData: MutableLiveData<EventLiveData<Boolean>> = MutableLiveData()


    init {
        //TODO do something for loading
        getHomeData()
        checkTodayCheckIn()
        setButtonText()
    }

    private fun checkTodayCheckIn(){
        if(preferencesHelper.latestCheckIn != 0L && DateUtils.isToday(preferencesHelper.latestCheckIn))
            todayCheckInEnable.value = false
    }

    private fun setButtonText(){
        checkInButtonText.value = if(todayCheckInEnable.value) resourceProvider.getString(R.string.register_checkin) else resourceProvider.getString(R.string.checkin_has_registered)
    }

    fun getHomeData() = homeDataUseCase.execute(compositeDisposable, this::onHomeDataResponse)

    fun getFormattedEventDate(position: Int): String{
        val item = eventListLiveData.value?.get(position)
        return item?.let {
            resourceProvider.getString(R.string.event_formatted_date, it.getDayOfWeek(), it.getDayOfMonth(), it.getMonthName(), it.getYear(), it.getHour())
        } ?: ""
    }

    fun checkIn(){
//        isLoading.value = true

        activityAction{ permissionUtil.request(it, arrayOf(GPS_PERMISSION), this::onPermissionResponse)  }

//        checkInUseCase.setParameters(CheckInType.MANUAL).execute(compositeDisposable, this::onCheckInResponse)
    }

    fun onHomeDataResponse(response: APIResponse<HomeData>) {
        when (response) {
            is SuccessResponse -> {
                cycle.value = response.value.cycle
                if(response.value.balance.balance < 0)
                    stateColor.value = resourceProvider.getColor(R.color.Ruddy)
                balance.value = resourceProvider.getString(R.string.number_toman, formatNumber(response.value.balance.balance))
                paid.value = resourceProvider.getString(R.string.total_paid_cycle, formatNumber(response.value.balance.paid))
                eventListLiveData.value = response.value.eventList
            }
            is ErrorResponse -> {
            }
        }
    }

    fun onCheckInResponse(response: APIResponse<CheckInResponse>){
        isLoading.value = false
        when(response){
            is SuccessResponse -> {
                preferencesHelper.latestCheckIn = response.value.timeEpoch
                todayCheckInEnable.value = false
                setButtonText()
            }
            is ErrorResponse -> { Log.d("CheckIn Response", "Failure") }
        }
    }

    fun onPermissionResponse(grantedPermissions: List<String>, deniedPermissions: List<String>){
        if(grantedPermissions.contains(GPS_PERMISSION)) {
            settingsLiveData.value = EventLiveData(true)
            checkInLiveData.value = EventLiveData(true)
        }
    }
}