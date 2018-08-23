package app.pashmak.com.pashmak.ui.login

import androidx.lifecycle.ViewModel
import app.pashmak.com.pashmak.di.qualifier.viewmodel.ViewModelKey
import dagger.multibindings.IntoMap

import dagger.Binds
import dagger.Module

@Module
abstract class LoginViewModelBuilder
{
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}