package app.pashmak.com.pashmak.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.databinding.ActivityMainBinding
import app.pashmak.com.pashmak.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(), HasSupportFragmentInjector {

    companion object {

        fun getCallingBundle(): Bundle = Bundle()
    }

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override val viewModel: MainViewModel by getLazyViewModel()
    override val layoutId: Int get() = R.layout.activity_main

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onViewInitialized(binding: ActivityMainBinding) {
        super.onViewInitialized(binding)
        binding.viewModel = viewModel

        viewModel.openHome()
    }
}