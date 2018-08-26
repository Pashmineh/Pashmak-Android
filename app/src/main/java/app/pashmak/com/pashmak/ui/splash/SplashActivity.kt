package app.pashmak.com.pashmak.ui.splash

import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivitySplashBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity


class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    override val binding: ActivitySplashBinding by lazy { DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash) }
    override val viewModel: SplashViewModel by getLazyViewModel()

    override fun onViewInitialized(binding: ActivitySplashBinding) {
        super.onViewInitialized(binding)
        runAnimation()
        viewModel.progressSplash()
    }

    private fun runAnimation() {
        val scaleXAnim = SpringAnimation(binding.TxtDesc, DynamicAnimation.SCALE_X).apply {
            val springForce = SpringForce()
            springForce.finalPosition = 1.5f
            springForce.stiffness = SpringForce.STIFFNESS_LOW
            springForce.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring = springForce
            setStartVelocity(600f)
        }

        val scaleYAnim = SpringAnimation(binding.TxtDesc, DynamicAnimation.SCALE_Y).apply {
            val springForce = SpringForce()
            springForce.finalPosition = 1.5f
            springForce.stiffness = SpringForce.STIFFNESS_LOW
            springForce.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
            spring = springForce
            setStartVelocity(600f)
        }

        scaleXAnim.start()
        scaleYAnim.start()
    }

}