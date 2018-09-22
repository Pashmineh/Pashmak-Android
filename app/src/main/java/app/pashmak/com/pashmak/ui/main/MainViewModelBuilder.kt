package app.pashmak.com.pashmak.ui.main

import androidx.lifecycle.ViewModel
import app.pashmak.com.pashmak.di.qualifier.viewmodel.ViewModelKey
import app.pashmak.com.pashmak.ui.main.home.HomeViewModel
import app.pashmak.com.pashmak.ui.main.polling.PollingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelBuilder
{
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PollingViewModel::class)
    abstract fun bindPollingViewModel(viewModel: PollingViewModel): ViewModel
}