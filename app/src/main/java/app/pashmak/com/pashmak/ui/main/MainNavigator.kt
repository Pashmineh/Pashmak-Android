package app.pashmak.com.pashmak.ui.main

import androidx.fragment.app.FragmentActivity
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.ui.base.BaseNavigator
import app.pashmak.com.pashmak.ui.main.checkin.CheckInFragment
import app.pashmak.com.pashmak.ui.main.home.HomeFragment
import app.pashmak.com.pashmak.ui.main.polling.PollingFragment
import javax.inject.Inject

class MainNavigator @Inject constructor(): BaseNavigator
{
    fun openHomeFragment(activity: FragmentActivity){
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance(), HomeFragment.TAG)
                .commitAllowingStateLoss()
    }

    fun openPollingFragment(activity: FragmentActivity){
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, PollingFragment.newInstance(), PollingFragment.TAG)
                .commitAllowingStateLoss()
    }

    fun openCheckInFragment(activity: FragmentActivity){
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CheckInFragment.newInstance(), CheckInFragment.TAG)
                .commitAllowingStateLoss()
    }
}