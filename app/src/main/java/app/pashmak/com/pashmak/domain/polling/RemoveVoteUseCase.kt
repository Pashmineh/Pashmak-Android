package app.pashmak.com.pashmak.domain.polling

import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.polling.VoteModel
import app.pashmak.com.pashmak.data.repository.polling.PollingDataRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class RemoveVoteUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val pollingDataRepository: PollingDataRepository
)
    : BaseUseCase<List<PollModel>>(errorUtil)
{
    private var pollId: Int = -1
    private var itemId: Int = -1

    fun setParameters(pollId: Int, itemId: Int): RemoveVoteUseCase{
        return this.also {
            this.pollId = pollId
            this.itemId = itemId
        }
    }

    override fun buildUseCaseObservable(): Flowable<List<PollModel>> {
        return pollingDataRepository.removeVote(VoteModel(itemId, pollId))
    }
}