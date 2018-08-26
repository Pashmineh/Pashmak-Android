package app.pashmak.com.pashmak.di.builder


import app.pashmak.com.pashmak.data.repository.login.LoginRepository
import app.pashmak.com.pashmak.data.repository.login.LoginRepositoryImpl
import dagger.Binds
import dagger.Module


@Module
interface RepositoryBuilder {
    @Binds
    fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}