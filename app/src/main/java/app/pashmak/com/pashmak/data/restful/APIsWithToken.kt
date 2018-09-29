package app.pashmak.com.pashmak.data.restful

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.polling.VoteModel
import app.pashmak.com.pashmak.data.model.transaction.DebtModel
import app.pashmak.com.pashmak.data.model.transaction.PaymentModel
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import io.reactivex.Flowable
import retrofit2.http.*

/**
 * The with-token apis should be defined here
 */
interface APIsWithToken{
    @GET("/api/home")
    fun getHomeData(): Flowable<HomeData>

    @GET("/api/checkins")
    fun getCheckInList(): Flowable<List<CheckInResponse>>

    @POST("/api/checkin")
    fun checkIn(@Query("checkinType") type: CheckInType): Flowable<CheckInResponse>

    @GET("/api/polls")
    fun getPolls(): Flowable<List<PollModel>>

    @HTTP(method = "DELETE", path = "/api/polls/vote", hasBody = true)
    fun removeVote(@Body voteModel: VoteModel): Flowable<List<PollModel>>

    @POST("/api/polls/vote")
    fun vote(@Body voteModel: VoteModel): Flowable<List<PollModel>>

    @GET("/api/debts")
    fun getDebtList(): Flowable<List<DebtModel>>

    @GET("/api/payments")
    fun getPaymentList(): Flowable<List<PaymentModel>>
}