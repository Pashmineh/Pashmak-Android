package app.pashmak.com.pashmak.data.repository.checkin

import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import io.reactivex.Flowable

interface CheckInRepository
{
    fun getCheckInList(): Flowable<List<CheckInResponse>>

    fun checkIn(checkInType: CheckInType): Flowable<CheckInResponse>
}