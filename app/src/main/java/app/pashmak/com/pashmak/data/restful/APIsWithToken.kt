package app.pashmak.com.pashmak.data.restful

import app.pashmak.com.pashmak.data.model.home.HomeData
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * The with-token apis should be defined here
 */
interface APIsWithToken{
    @GET("/api/home")
    fun getHomeData(): Flowable<HomeData>
}