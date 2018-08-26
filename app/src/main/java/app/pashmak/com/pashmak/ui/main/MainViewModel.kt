package app.pashmak.com.pashmak.ui.main

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.data.source.preference.AppPreferencesHelper
import app.pashmak.com.pashmak.domain.home.HomeDataUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.getAvatarUrl
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class MainViewModel
    @Inject constructor(
             preferencesHelper: AppPreferencesHelper,
             homeDataUseCase: HomeDataUseCase
    )
    : BaseViewModel()
{
    val fullName: String = "${preferencesHelper.firstName} ${preferencesHelper.lastName}"
    val avatar  : String = getAvatarUrl(preferencesHelper.userPhone)
    var balance : NonNullLiveData<String> = NonNullLiveData("0")
    var paid : NonNullLiveData<String> = NonNullLiveData("0")
    var cycle : NonNullLiveData<String> = NonNullLiveData("")


    init {
        //TODO do something for loading
        homeDataUseCase.execute(compositeDisposable, this::onHomeDataResponse)
    }

    fun onHomeDataResponse(response: APIResponse<HomeData>){
        when(response){
            is SuccessResponse -> {
                cycle.value = response.value.cycle
                balance.value = response.value.balance.balance.toString()
                paid.value = response.value.balance.paid.toString()
            }
            is ErrorResponse -> {}
        }
    }
}