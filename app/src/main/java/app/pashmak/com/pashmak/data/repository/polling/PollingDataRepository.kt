package app.pashmak.com.pashmak.data.repository.polling

import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.polling.VoteModel
import io.reactivex.Flowable

interface PollingDataRepository
{
    fun getPollings(): Flowable<List<PollModel>>

    fun vote(voteModel: VoteModel): Flowable<List<PollModel>>

    fun removeVote(voteModel: VoteModel): Flowable<List<PollModel>>

}