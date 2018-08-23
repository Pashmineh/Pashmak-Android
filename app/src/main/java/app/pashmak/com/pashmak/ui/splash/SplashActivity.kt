package app.pashmak.com.pashmak.ui.splash

import androidx.databinding.DataBindingUtil
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivitySplashBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity

class SplashActivity: BaseActivity<SplashViewModel, ActivitySplashBinding>()
{
    override val binding: ActivitySplashBinding by lazy { DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash) }
    override val viewModel: SplashViewModel by getLazyViewModel()

    override fun onViewInitialized(binding: ActivitySplashBinding) {
        super.onViewInitialized(binding)
        viewModel.progressSplash()
    }

}