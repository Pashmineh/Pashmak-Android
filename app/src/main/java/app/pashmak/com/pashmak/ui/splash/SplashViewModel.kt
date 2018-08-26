package app.pashmak.com.pashmak.ui.splash

import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.providers.BaseResourceProvider
import app.pashmak.com.pashmak.util.providers.BaseViewProvider
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel
@Inject constructor(
        private val navigator: SplashNavigator,
        private val resourceProvider: BaseResourceProvider,
        private val viewProvider: BaseViewProvider)
    : BaseViewModel() {
    companion object {
        private const val WAITING_TIME = 2000L
    }

    fun progressSplash() {
        compositeDisposable.add(Completable.complete()
                .subscribeOn(Schedulers.computation())
                .delay(WAITING_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { openLoginActivity() })
    }

    private fun openLoginActivity() =
            activityAction { navigator.openLoginActivity(it, viewProvider.findView(it, R.id.__img_logo)!!, resourceProvider.getString(R.string.login_shared_element_name)) }
}