package app.pashmak.com.pashmak.ui.main.checkin

import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.domain.checkin.GetCheckInListUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class CheckInViewModel
    @Inject constructor(
            private val getCheckInListUseCase: GetCheckInListUseCase
    )
    : BaseViewModel()
{

    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val checkInListLiveDatas: MutableLiveData<List<CheckInResponse>> = MutableLiveData()

    fun getCheckInList(){
        isLoading.value = true
        getCheckInListUseCase.execute(compositeDisposable, this::onCheckInListResponse)
    }

    private fun onCheckInListResponse(response: APIResponse<List<CheckInResponse>>){
        isLoading.value = false
        when(response){
            is SuccessResponse -> {
                checkInListLiveDatas.value = response.value
            }
            is ErrorResponse -> {}
        }
    }
}