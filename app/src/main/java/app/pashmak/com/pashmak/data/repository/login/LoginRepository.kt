package app.pashmak.com.pashmak.data.repository.login

import app.pashmak.com.pashmak.data.model.login.LoginResponse
import io.reactivex.Flowable

interface LoginRepository
{
    fun login(userName: String, password: String, pushToken: String): Flowable<LoginResponse>
}