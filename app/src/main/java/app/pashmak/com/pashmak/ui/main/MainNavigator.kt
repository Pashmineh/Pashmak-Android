package app.pashmak.com.pashmak.ui.main

import androidx.fragment.app.FragmentActivity
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.ui.base.BaseNavigator
import app.pashmak.com.pashmak.ui.main.home.HomeFragment
import javax.inject.Inject

class MainNavigator @Inject constructor(): BaseNavigator
{
    fun openHomeFragment(activity: FragmentActivity){
        activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.container, HomeFragment.newInstance(), HomeFragment.TAG)
                .commitAllowingStateLoss()
    }
}