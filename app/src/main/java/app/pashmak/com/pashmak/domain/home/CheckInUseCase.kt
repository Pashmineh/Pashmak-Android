package app.pashmak.com.pashmak.domain.home

import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import app.pashmak.com.pashmak.data.repository.home.HomeDataRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class CheckInUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val homeDataRepository: HomeDataRepository
                    )
    : BaseUseCase<CheckInResponse>(errorUtil)
{
    private lateinit var checkInType: CheckInType

    fun setParameters(checkInType: CheckInType): CheckInUseCase{
        return this.also { it.checkInType = checkInType }
    }

    override fun buildUseCaseObservable(): Flowable<CheckInResponse> {
        return homeDataRepository.checkIn(checkInType)
    }
}