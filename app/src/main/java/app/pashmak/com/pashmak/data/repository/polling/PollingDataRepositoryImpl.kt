package app.pashmak.com.pashmak.data.repository.polling

import app.pashmak.com.pashmak.data.model.polling.VoteModel
import javax.inject.Inject

class PollingDataRepositoryImpl
@Inject constructor(private val pollingDataRepositoryFactory: PollingDataRepositoryFactory)
    : PollingDataRepository {
    override fun getPollings() = pollingDataRepositoryFactory.getPolls()

    override fun vote(voteModel: VoteModel) = pollingDataRepositoryFactory.vote(voteModel)

    override fun removeVote(voteModel: VoteModel) = pollingDataRepositoryFactory.removeVote(voteModel)

}