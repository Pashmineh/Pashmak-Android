package app.pashmak.com.pashmak.data.repository.message

import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import javax.inject.Inject

class MessageDataRepositoryFactory
@Inject constructor(private val cloudRepository: BaseCloudRepository)
{
    fun getMessageList() = cloudRepository.getMessageList()
}