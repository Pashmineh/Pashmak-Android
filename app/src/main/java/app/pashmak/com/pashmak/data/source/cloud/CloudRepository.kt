package app.pashmak.com.pashmak.data.source.cloud

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.login.LoginRequest
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import app.pashmak.com.pashmak.data.restful.APIs
import app.pashmak.com.pashmak.data.restful.APIsWithToken
import io.reactivex.Flowable

/**
 * The main implementation of [BaseCloudRepository] that call api services directly
 * @param apIs instance of without-token apis
 * @param apIsWithToken instance of with-token apis
 */
class CloudRepository(private val apIs: APIs, private val apIsWithToken: APIsWithToken) : BaseCloudRepository {

    override fun login(loginModel: LoginRequest): Flowable<LoginResponse> {
        return apIs.requestRegisterUser(loginModel)
    }

    override fun getHomeData(): Flowable<HomeData> = apIsWithToken.getHomeData()

    override fun checkin(checkInType: CheckInType): Flowable<CheckInResponse> = apIsWithToken.checkIn(checkInType)

}
