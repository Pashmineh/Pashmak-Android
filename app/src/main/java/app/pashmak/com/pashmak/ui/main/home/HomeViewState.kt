package app.pashmak.com.pashmak.ui.main.home

import android.graphics.drawable.Drawable
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.util.formatNumber
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import javax.inject.Inject

class HomeViewState
@Inject constructor(private val resourceProvider: BaseResourceProvider)
{

    val stateColor: NonNullLiveData<Int> = NonNullLiveData(resourceProvider.getColor(R.color.Kelly_green))
    var placeHolder: Drawable = resourceProvider.getDrawable(R.drawable.vector_person_48dp)!!
    var fullName: String = ""
    var avatarUrl: String = ""

    var balance: NonNullLiveData<String> = NonNullLiveData("0")
    var paid: NonNullLiveData<String> = NonNullLiveData("0")
    var cycle: NonNullLiveData<String> = NonNullLiveData("")

    val todayCheckInEnable: NonNullLiveData<Boolean> = NonNullLiveData(true)
    val checkInButtonText: NonNullLiveData<String> = NonNullLiveData("")
    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)

    fun initialize(
            fullName: String,
            avatarUrl: String
    ){
        this.fullName = fullName
        this.avatarUrl = avatarUrl
        setButtonText()
    }

    private fun setButtonText() {
        checkInButtonText.value = if (todayCheckInEnable.value) resourceProvider.getString(R.string.register_checkin) else resourceProvider.getString(R.string.checkin_has_registered)
    }

    fun setHomeDataValues(data: HomeData){
        cycle.value = data.cycle
        if (data.balance.balance < 0)
            stateColor.value = resourceProvider.getColor(R.color.Ruddy)
        balance.value = resourceProvider.getString(R.string.number_toman, formatNumber(data.balance.balance))
        paid.value = resourceProvider.getString(R.string.total_paid_cycle, formatNumber(data.balance.paid))
    }

    fun onSuccessfulCheckIn(){
        todayCheckInEnable.value = false
        setButtonText()
    }
}