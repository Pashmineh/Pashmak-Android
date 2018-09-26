package app.pashmak.com.pashmak.data.repository.checkin

import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import io.reactivex.Flowable
import javax.inject.Inject

class CheckInRepositoryImpl
@Inject constructor(private val checkInRepositoryFactory: CheckInRepositoryFactory)
    : CheckInRepository
{
    override fun checkIn(checkInType: CheckInType): Flowable<CheckInResponse> = checkInRepositoryFactory.checkIn(checkInType)

    override fun getCheckInList(): Flowable<List<CheckInResponse>> = checkInRepositoryFactory.getCheckInList()
}