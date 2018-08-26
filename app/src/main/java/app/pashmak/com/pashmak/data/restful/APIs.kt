package app.pashmak.com.pashmak.data.restful

import app.pashmak.com.pashmak.data.model.login.LoginRequest
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST


/**
 * The without-token apis should be defined here
 */
interface APIs {
    @POST("/api/authenticate")
    fun requestRegisterUser(@Body model: LoginRequest): Flowable<LoginResponse>
}