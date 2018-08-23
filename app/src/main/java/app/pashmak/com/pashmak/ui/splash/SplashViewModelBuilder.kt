package app.pashmak.com.pashmak.ui.splash

import androidx.lifecycle.ViewModel
import app.pashmak.com.pashmak.di.qualifier.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SplashViewModelBuilder
{
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}