package app.pashmak.com.pashmak.data.repository.checkin

import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import javax.inject.Inject

class CheckInRepositoryFactory
@Inject constructor(private val cloudRepository: BaseCloudRepository)
{
    fun checkIn(checkInType: CheckInType) = cloudRepository.checkin(checkInType)

    fun getCheckInList() = cloudRepository.getCheckInList()
}