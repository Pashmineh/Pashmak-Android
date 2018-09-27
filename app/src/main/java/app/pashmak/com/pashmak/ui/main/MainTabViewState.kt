package app.pashmak.com.pashmak.ui.main

import androidx.fragment.app.FragmentActivity
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class MainTabViewState
@Inject constructor(private val mNavigator: MainNavigator)
{
    val homeTintRes = NonNullLiveData(R.color.Davy_grey)
    val messagesTintRes = NonNullLiveData(R.color.Davy_grey)
    val pollingTintRes = NonNullLiveData(R.color.Davy_grey)
    val checkInTintRes = NonNullLiveData(R.color.Davy_grey)
    val transactionTintRes = NonNullLiveData(R.color.Davy_grey)

    var selectedTab : MainTabEnum? = null

    fun select(tab: MainTabEnum, fragmentActivity: FragmentActivity) {

        setDefaultTints()

        when (tab){
            MainTabEnum.HOME -> {
                mNavigator.openHomeFragment(fragmentActivity)
                homeTintRes.value = R.color.Dark_tangerine
            }
            MainTabEnum.MESSAGES -> {}
            MainTabEnum.POLLING -> {
                mNavigator.openPollingFragment(fragmentActivity)
                pollingTintRes.value = R.color.Dark_tangerine
            }
            MainTabEnum.CHECKIN -> {
                mNavigator.openCheckInFragment(fragmentActivity)
                checkInTintRes.value = R.color.Dark_tangerine
            }
            MainTabEnum.TRANSACTIONS -> {}
        }

        selectedTab = tab
    }

    private fun setDefaultTints(){
        homeTintRes.value = R.color.Davy_grey
        messagesTintRes.value = R.color.Davy_grey
        pollingTintRes.value = R.color.Davy_grey
        checkInTintRes.value = R.color.Davy_grey
        transactionTintRes.value = R.color.Davy_grey
    }
}