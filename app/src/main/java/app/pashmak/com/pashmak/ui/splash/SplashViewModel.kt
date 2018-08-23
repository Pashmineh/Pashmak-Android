package app.pashmak.com.pashmak.ui.splash

import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.ui.login.LoginActivity
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel
    @Inject constructor(private val navigator: SplashNavigator)
    : BaseViewModel()
{
    companion object {
        private const val WAITING_TIME = 2000L
    }

    fun progressSplash(){
        compositeDisposable.add(Completable.complete()
                .subscribeOn(Schedulers.computation())
                .delay(WAITING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { openLoginActivity() })
    }

    private fun openLoginActivity() = activityAction{ navigator.startActivity(it, LoginActivity::class.java, LoginActivity.getCallingBundle()) }
}