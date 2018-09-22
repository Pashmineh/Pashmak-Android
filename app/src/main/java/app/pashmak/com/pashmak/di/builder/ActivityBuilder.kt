package app.pashmak.com.pashmak.di.builder

import app.pashmak.com.pashmak.ui.login.LoginActivity
import app.pashmak.com.pashmak.ui.main.MainActivity
import app.pashmak.com.pashmak.ui.main.MainFragmentProvider
import app.pashmak.com.pashmak.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder
{
    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [MainFragmentProvider::class])
    internal abstract fun bindMainActivity(): MainActivity
}