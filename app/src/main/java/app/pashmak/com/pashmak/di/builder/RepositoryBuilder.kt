package app.pashmak.com.pashmak.di.builder


import app.pashmak.com.pashmak.data.repository.checkin.CheckInRepository
import app.pashmak.com.pashmak.data.repository.checkin.CheckInRepositoryImpl
import app.pashmak.com.pashmak.data.repository.home.HomeDataRepository
import app.pashmak.com.pashmak.data.repository.home.HomeDataRepositoryImpl
import app.pashmak.com.pashmak.data.repository.login.LoginRepository
import app.pashmak.com.pashmak.data.repository.login.LoginRepositoryImpl
import app.pashmak.com.pashmak.data.repository.message.MessageDataRepository
import app.pashmak.com.pashmak.data.repository.message.MessageDataRepositoryImpl
import app.pashmak.com.pashmak.data.repository.polling.PollingDataRepository
import app.pashmak.com.pashmak.data.repository.polling.PollingDataRepositoryImpl
import app.pashmak.com.pashmak.data.repository.transaction.TransactionDataRepository
import app.pashmak.com.pashmak.data.repository.transaction.TransactionDataRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface RepositoryBuilder {
    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    fun bindHomeDataRepository(homeDataRepositoryImpl: HomeDataRepositoryImpl): HomeDataRepository

    @Binds
    fun bindPollingDataRepository(pollingDataRepositoryImpl: PollingDataRepositoryImpl): PollingDataRepository

    @Binds
    fun bindCheckInRepository(checkInRepositoryImpl: CheckInRepositoryImpl): CheckInRepository

    @Binds
    fun bindTransactionRepository(transactionRepository: TransactionDataRepositoryImpl): TransactionDataRepository

    @Binds
    fun bindMessageRepository(messageDataRepositoryImpl: MessageDataRepositoryImpl): MessageDataRepository
}