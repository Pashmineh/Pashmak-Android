package app.pashmak.com.pashmak.ui.main.checkin

import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.data.source.preference.AppPreferencesHelper
import app.pashmak.com.pashmak.domain.checkin.CheckInUseCase
import app.pashmak.com.pashmak.domain.checkin.GetCheckInListUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.PermissionUtil
import app.pashmak.com.pashmak.util.beacon.PashmakBeaconUtil
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class CheckInViewModel
    @Inject constructor(
            private val getCheckInListUseCase: GetCheckInListUseCase,
            private val checkInUseCase: CheckInUseCase,
            private val preferencesHelper: AppPreferencesHelper,
            val pashmakBeaconUtil: PashmakBeaconUtil,
            val permissionUtil: PermissionUtil
            )
    : BaseViewModel()
{

    companion object {
        const val REQUEST_CHECK_LOCATION_SETTINGS = 232
        private val GPS_PERMISSION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    }

    val checkInLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val todayCheckInVisible: NonNullLiveData<Boolean> = NonNullLiveData(true)
    val settingsLiveData: MutableLiveData<app.pashmak.com.pashmak.util.livedata.Event<Boolean>> = MutableLiveData()
    val messageLiveData: MutableLiveData<String> = MutableLiveData()

    val pageLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val checkInListLiveDatas: MutableLiveData<List<CheckInResponse>> = MutableLiveData()

    init {
        checkTodayCheckIn()
    }

    fun getCheckInList(){
        pageLoading.value = true
        getCheckInListUseCase.execute(compositeDisposable, this::onCheckInListResponse)
    }

    fun prepareCheckIn() {
        fragmentAction { permissionUtil.request(it, arrayOf(GPS_PERMISSION), this::onPermissionResponse) }
    }

    fun checkBeaconState() {
        fragmentAction{ pashmakBeaconUtil.checkMyBeacon(it, this::onBeaconFound, this::onFindingBeaconFailed) }
        checkInLoading.value = true
        pageLoading.value = true
    }

    fun checkIn() {
        checkInUseCase.setParameters(CheckInType.MANUAL).execute(compositeDisposable, this::onCheckInResponse)
    }

    private fun onBeaconFound(){
        checkIn()
    }

    private fun onCheckInResponse(response: APIResponse<CheckInResponse>) {
        checkInLoading.value = false
        pageLoading.value = false
        when (response) {
            is SuccessResponse -> {
                preferencesHelper.latestCheckIn = response.value.checkInTimeValue
                todayCheckInVisible.value = false
            }
            is ErrorResponse -> {
                Log.d("CheckIn Response", "Failure")
            }
        }
    }

    private fun onFindingBeaconFailed(hasSettingsIssue: Boolean){
        checkInLoading.value = false
        pageLoading.value = false
        if(!hasSettingsIssue){
            messageLiveData.value = "Pasho Boro Sherkat"
        }
    }

    private fun onPermissionResponse(grantedPermissions: List<String>, deniedPermissions: List<String>) {
        if (grantedPermissions.contains(GPS_PERMISSION)) {
            settingsLiveData.value = app.pashmak.com.pashmak.util.livedata.Event(true)
        }
    }

    private fun checkTodayCheckIn() {
        if (preferencesHelper.latestCheckIn != 0L && DateUtils.isToday(preferencesHelper.latestCheckIn))
            todayCheckInVisible.value = false
    }

    private fun onCheckInListResponse(response: APIResponse<List<CheckInResponse>>){
        pageLoading.value = false
        when(response){
            is SuccessResponse -> {
                val sortedList = response.value.sortedBy { it.checkInTimeValue }
                checkInListLiveDatas.value = sortedList
            }
            is ErrorResponse -> {}
        }
    }
}