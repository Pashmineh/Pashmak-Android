package app.pashmak.com.pashmak.ui.splash

import android.os.Bundle
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.ui.base.BaseActivity

class SplashActivity: BaseActivity<SplashViewModel/*, ActivitySplashBinding*/>()
{
    override val layoutId: Int get() = R.layout.activity_splash
    override val viewModel: SplashViewModel by getLazyViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
//        binding.setLifecycleOwner(this)
    }

}