package app.pashmak.com.pashmak.data.restful

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * The with-token apis should be defined here
 */
interface APIsWithToken{
    @GET("/api/home")
    fun getHomeData(): Flowable<HomeData>

    @POST("/api/checkin")
    fun checkIn(@Query("checkinType") type: CheckInType): Flowable<CheckInResponse>
}