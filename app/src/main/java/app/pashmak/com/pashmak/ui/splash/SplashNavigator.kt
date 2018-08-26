package app.pashmak.com.pashmak.ui.splash

import android.view.View
import androidx.fragment.app.FragmentActivity
import app.pashmak.com.pashmak.ui.base.BaseNavigator
import javax.inject.Inject
import androidx.core.app.ActivityOptionsCompat
import app.pashmak.com.pashmak.ui.login.LoginActivity


class SplashNavigator @Inject constructor(): BaseNavigator
{
    fun openLoginActivity(activity: FragmentActivity, sharedView: View, sharedName: String){
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView , sharedName)
        startActivity(activity, LoginActivity::class.java, LoginActivity.getCallingBundle(), options.toBundle()!!)
    }
}