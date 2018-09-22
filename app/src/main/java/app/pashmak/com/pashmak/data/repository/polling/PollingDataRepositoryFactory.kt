package app.pashmak.com.pashmak.data.repository.polling

import app.pashmak.com.pashmak.data.model.polling.VoteModel
import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import javax.inject.Inject

class PollingDataRepositoryFactory
@Inject constructor(private val cloudRepository: BaseCloudRepository)
{
    fun getPolls() = cloudRepository.getPollings()

    fun vote(voteModel: VoteModel) = cloudRepository.vote(voteModel)

    fun removeVote(voteModel: VoteModel) = cloudRepository.removeVote(voteModel)
}