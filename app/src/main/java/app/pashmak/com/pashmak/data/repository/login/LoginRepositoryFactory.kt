package app.pashmak.com.pashmak.data.repository.login

import app.pashmak.com.pashmak.data.model.login.LoginRequest
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import io.reactivex.Flowable
import javax.inject.Inject

class LoginRepositoryFactory
@Inject constructor(private val repository: BaseCloudRepository)
{
    fun login(loginRequest: LoginRequest): Flowable<LoginResponse> = repository.login(loginRequest)
}