package app.pashmak.com.pashmak.ui.main

import app.pashmak.com.pashmak.ui.base.BaseViewModel
import javax.inject.Inject
import app.pashmak.com.pashmak.util.livedata.Event as EventLiveData


class MainViewModel
@Inject constructor(
        val mainTabViewState: MainTabViewState
) : BaseViewModel() {

    fun openHome() = activityAction{ mainTabViewState.select(MainTabEnum.HOME, it)}

    fun openMessages() {}

    fun openPolling() = activityAction{ mainTabViewState.select(MainTabEnum.POLLING, it)}

    fun openCheckIn() = activityAction{ mainTabViewState.select(MainTabEnum.CHECKIN, it)}

    fun openTransactions() = activityAction{ mainTabViewState.select(MainTabEnum.TRANSACTIONS, it)}
}