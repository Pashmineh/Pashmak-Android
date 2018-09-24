package app.pashmak.com.pashmak.domain.polling

import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.repository.polling.PollingDataRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class GetPollsUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val pollingDataRepository: PollingDataRepository
)
    : BaseUseCase<List<PollModel>>(errorUtil)
{
    override fun buildUseCaseObservable(): Flowable<List<PollModel>> {
        return pollingDataRepository.getPollings()
    }
}