package app.pashmak.com.pashmak.data.restful

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.polling.VoteModel
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * The with-token apis should be defined here
 */
interface APIsWithToken{
    @GET("/api/home")
    fun getHomeData(): Flowable<HomeData>

    @POST("/api/checkin")
    fun checkIn(@Query("checkinType") type: CheckInType): Flowable<CheckInResponse>

    @GET("/api/polls")
    fun getPolls(): Flowable<List<PollModel>>

    @DELETE("/api/polls/vote")
    fun removeVote(@Body voteModel: VoteModel): Flowable<List<PollModel>>

    @POST("/api/polls/vote")
    fun vote(@Body voteModel: VoteModel): Flowable<List<PollModel>>
}