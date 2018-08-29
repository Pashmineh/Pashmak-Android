package app.pashmak.com.pashmak.data.repository.home

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.home.checkin.CheckInType
import io.reactivex.Flowable

interface HomeDataRepository {

    fun getHomeData(): Flowable<HomeData>

    fun checkIn(checkInType: CheckInType): Flowable<CheckInResponse>
}