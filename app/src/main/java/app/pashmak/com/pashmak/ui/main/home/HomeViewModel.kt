package app.pashmak.com.pashmak.ui.main.home

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
import app.pashmak.com.pashmak.util.getAvatarUrl
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import javax.inject.Inject

class HomeViewModel
    @Inject constructor(
            private val homeDataUseCase: HomeDataUseCase,
            private val resourceProvider: BaseResourceProvider,
            preferencesHelper: AppPreferencesHelper,
            val viewState: HomeViewState
    )
    : BaseViewModel()
{

    val eventListLiveData: MutableLiveData<List<Event>> = MutableLiveData()

    init {
        //TODO do something for loading
        viewState.initialize(
                "${preferencesHelper.firstName} ${preferencesHelper.lastName}",
                getAvatarUrl(preferencesHelper.userPhone)
        )
        getHomeData()
    }

    fun getHomeData() = homeDataUseCase.execute(compositeDisposable, this::onHomeDataResponse)

    fun getFormattedEventDate(position: Int): String {
        val item = eventListLiveData.value?.get(position)
        return item?.let {
            resourceProvider.getString(R.string.event_formatted_date, it.getDayOfWeek(), it.getDayOfMonth(), it.getMonthName(), it.getYear(), it.getHour())
        } ?: ""
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
}