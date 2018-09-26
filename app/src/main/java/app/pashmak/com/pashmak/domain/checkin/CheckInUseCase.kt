package app.pashmak.com.pashmak.domain.checkin

import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import app.pashmak.com.pashmak.data.repository.checkin.CheckInRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class CheckInUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val checkInRepository: CheckInRepository
) : BaseUseCase<CheckInResponse>(errorUtil)
{
    private lateinit var checkInType: CheckInType

    fun setParameters(checkInType: CheckInType): CheckInUseCase {
        return this.also { it.checkInType = checkInType }
    }

    override fun buildUseCaseObservable(): Flowable<CheckInResponse> {
        return checkInRepository.checkIn(checkInType)
    }
}