package app.pashmak.com.pashmak.ui.main

import app.pashmak.com.pashmak.ui.main.checkin.CheckInFragment
import app.pashmak.com.pashmak.ui.main.home.HomeFragment
import app.pashmak.com.pashmak.ui.main.polling.PollingFragment
import app.pashmak.com.pashmak.ui.main.transaction.TransactionListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider {

    @ContributesAndroidInjector
    internal abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun providePollingFragment(): PollingFragment

    @ContributesAndroidInjector
    internal abstract fun provideCheckInFragment(): CheckInFragment

    @ContributesAndroidInjector
    internal abstract fun provideTransactionListFragment(): TransactionListFragment
}