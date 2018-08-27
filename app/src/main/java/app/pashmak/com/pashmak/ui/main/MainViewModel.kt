package app.pashmak.com.pashmak.ui.main

import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.home.Event
import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.data.source.preference.AppPreferencesHelper
import app.pashmak.com.pashmak.domain.home.HomeDataUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.formatNumber
import app.pashmak.com.pashmak.util.getAvatarUrl
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import javax.inject.Inject

class MainViewModel
@Inject constructor(
        preferencesHelper: AppPreferencesHelper,
        homeDataUseCase: HomeDataUseCase,
        private val resourceProvider: BaseResourceProvider
) : BaseViewModel() {

    val stateColor: NonNullLiveData<Int> = NonNullLiveData(resourceProvider.getColor(R.color.Kelly_green))
    val placeHolder: Drawable = resourceProvider.getDrawable(R.drawable.vector_person_48dp)!!
    val fullName: String = "${preferencesHelper.firstName} ${preferencesHelper.lastName}"
    val avatar: String = getAvatarUrl(preferencesHelper.userPhone)

    var balance: NonNullLiveData<String> = NonNullLiveData("0")
    var paid: NonNullLiveData<String> = NonNullLiveData("0")
    var cycle: NonNullLiveData<String> = NonNullLiveData("")

    val eventListLiveData: MutableLiveData<List<Event>> = MutableLiveData()


    init {
        //TODO do something for loading
        homeDataUseCase.execute(compositeDisposable, this::onHomeDataResponse)
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
}