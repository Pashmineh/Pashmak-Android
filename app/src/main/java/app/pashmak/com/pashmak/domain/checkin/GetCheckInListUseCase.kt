package app.pashmak.com.pashmak.domain.checkin

import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.repository.checkin.CheckInRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class GetCheckInListUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val checkInRepository: CheckInRepository
)
    :BaseUseCase<List<CheckInResponse>>(errorUtil)
{
    override fun buildUseCaseObservable(): Flowable<List<CheckInResponse>> {
        return checkInRepository.getCheckInList()
    }
}