package app.pashmak.com.pashmak.data.repository.home

import app.pashmak.com.pashmak.data.model.home.HomeData
import io.reactivex.Flowable

interface HomeDataRepository {

    fun getHomeData(): Flowable<HomeData>
}