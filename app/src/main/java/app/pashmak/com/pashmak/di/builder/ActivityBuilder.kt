package app.pashmak.com.pashmak.di.builder

import app.pashmak.com.pashmak.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder
{
    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity
}