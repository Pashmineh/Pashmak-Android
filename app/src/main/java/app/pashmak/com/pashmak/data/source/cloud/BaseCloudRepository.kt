package app.pashmak.com.pashmak.data.source.cloud

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.login.LoginRequest
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import io.reactivex.Flowable


/**
 * Every api service defined here as a contract and will be implemented for real lifecycle in
 * [CloudRepository]
 */
interface BaseCloudRepository {

    fun login(loginModel: LoginRequest): Flowable<LoginResponse>

    fun getHomeData(): Flowable<HomeData>

    fun checkin(checkInType: CheckInType): Flowable<CheckInResponse>
}