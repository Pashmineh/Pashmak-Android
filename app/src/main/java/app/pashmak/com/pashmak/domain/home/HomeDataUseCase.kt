package app.pashmak.com.pashmak.domain.home

import app.pashmak.com.pashmak.data.model.home.HomeData
import app.pashmak.com.pashmak.data.repository.home.HomeDataRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class HomeDataUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val homeDataRepository: HomeDataRepository)
    : BaseUseCase<HomeData>(errorUtil)
{
    override fun buildUseCaseObservable(): Flowable<HomeData> {
        return homeDataRepository.getHomeData()
    }
}