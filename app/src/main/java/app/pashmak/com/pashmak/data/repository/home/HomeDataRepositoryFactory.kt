package app.pashmak.com.pashmak.data.repository.home

import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import javax.inject.Inject

class HomeDataRepositoryFactory
@Inject constructor(private val cloudRepository: BaseCloudRepository)
{
    fun getHomeData() = cloudRepository.getHomeData()
}