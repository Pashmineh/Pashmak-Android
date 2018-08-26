package app.pashmak.com.pashmak.ui.splash

import android.content.Intent
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

        val intent = Intent(activity, LoginActivity::class.java)
        activity.startActivity(intent, options.toBundle())
    }
}