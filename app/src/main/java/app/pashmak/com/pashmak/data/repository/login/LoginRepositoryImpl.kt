package app.pashmak.com.pashmak.data.repository.login

import app.pashmak.com.pashmak.data.model.login.LoginRequest
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import io.reactivex.Flowable
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(private val loginRepositoryFactory: LoginRepositoryFactory): LoginRepository
{
    override fun login(userName: String, password: String): Flowable<LoginResponse>
    {
        return loginRepositoryFactory.login(LoginRequest(userName, password))
    }
}