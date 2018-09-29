package app.pashmak.com.pashmak.ui.main

import androidx.lifecycle.ViewModel
import app.pashmak.com.pashmak.di.qualifier.viewmodel.ViewModelKey
import app.pashmak.com.pashmak.ui.main.checkin.CheckInViewModel
import app.pashmak.com.pashmak.ui.main.home.HomeViewModel
import app.pashmak.com.pashmak.ui.main.messages.MessageListViewModel
import app.pashmak.com.pashmak.ui.main.polling.PollingViewModel
import app.pashmak.com.pashmak.ui.main.transaction.TransactionListViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(CheckInViewModel::class)
    abstract fun bindCheckInViewModel(viewModel: CheckInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionListViewModel::class)
    abstract fun bindTransactionListViewModelViewModel(viewModel: TransactionListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MessageListViewModel::class)
    abstract fun bindMessageListViewModelViewModel(viewModel: MessageListViewModel): ViewModel
}