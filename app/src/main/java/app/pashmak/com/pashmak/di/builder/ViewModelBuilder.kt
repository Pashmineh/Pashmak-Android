package app.pashmak.com.pashmak.di.builder


import androidx.lifecycle.ViewModelProvider
import app.pashmak.com.pashmak.ui.login.LoginViewModelBuilder
import app.pashmak.com.pashmak.ui.main.MainViewModelBuilder
import app.pashmak.com.pashmak.ui.splash.SplashViewModelBuilder
import app.pashmak.com.pashmak.viewmodel.PashmakViewModelFactory
import dagger.Binds
import dagger.Module


@Module(includes = [
    SplashViewModelBuilder::class,
    LoginViewModelBuilder::class,
    MainViewModelBuilder::class
])
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(archViewModelFactory: PashmakViewModelFactory): ViewModelProvider.Factory
}