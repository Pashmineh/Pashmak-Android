package app.pashmak.com.pashmak.data.repository.message

import javax.inject.Inject

class MessageDataRepositoryImpl
@Inject constructor(private val messageDataRepositoryFactory: MessageDataRepositoryFactory)
    : MessageDataRepository
{
    override fun getMessageList() = messageDataRepositoryFactory.getMessageList()
}