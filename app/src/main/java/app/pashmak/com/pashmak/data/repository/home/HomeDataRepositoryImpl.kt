package app.pashmak.com.pashmak.data.repository.home

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.model.checkin.CheckInResponse
import app.pashmak.com.pashmak.data.model.checkin.CheckInType
import io.reactivex.Flowable
import javax.inject.Inject

class HomeDataRepositoryImpl
    @Inject constructor(private val homeDataRepositoryFactory: HomeDataRepositoryFactory)
    : HomeDataRepository
{
    override fun getHomeData(): Flowable<HomeData> = homeDataRepositoryFactory.getHomeData()

}