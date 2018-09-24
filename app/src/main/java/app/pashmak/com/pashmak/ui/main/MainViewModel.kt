package app.pashmak.com.pashmak.ui.main

import app.pashmak.com.pashmak.ui.base.BaseViewModel
import javax.inject.Inject
import app.pashmak.com.pashmak.util.livedata.Event as EventLiveData


class MainViewModel
@Inject constructor(
        private val navigator: MainNavigator
) : BaseViewModel() {

    fun openHome() = activityAction{ navigator.openHomeFragment(it) }

    fun openMessages() {}

    fun openPolling() = activityAction{ navigator.openPollingFragment(it) }

    fun openEnterExit() {}

    fun openTransactions() {}
}