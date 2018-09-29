package app.pashmak.com.pashmak.data.source.cloud

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import app.pashmak.com.pashmak.data.model.login.LoginRequest
import app.pashmak.com.pashmak.data.model.login.LoginResponse
import app.pashmak.com.pashmak.data.model.message.MessageModel
import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.polling.VoteModel
import app.pashmak.com.pashmak.data.model.transaction.DebtModel
import app.pashmak.com.pashmak.data.model.transaction.PaymentModel
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import io.reactivex.Flowable


/**
 * Every api service defined here as a contract and will be implemented for real lifecycle in
 * [CloudRepository]
 */
interface BaseCloudRepository {

    fun login(loginModel: LoginRequest): Flowable<LoginResponse>

    fun getHomeData(): Flowable<HomeData>

    fun getCheckInList(): Flowable<List<CheckInResponse>>

    fun checkin(checkInType: CheckInType): Flowable<CheckInResponse>

    fun getPollings(): Flowable<List<PollModel>>

    fun vote(voteModel: VoteModel): Flowable<List<PollModel>>

    fun removeVote(voteModel: VoteModel): Flowable<List<PollModel>>

    fun getDebtList(): Flowable<List<DebtModel>>

    fun getPaymentList(): Flowable<List<PaymentModel>>

    fun getMessageList(): Flowable<List<MessageModel>>
}