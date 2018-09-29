package app.pashmak.com.pashmak.data.repository.message

import app.pashmak.com.pashmak.data.model.message.MessageModel
import io.reactivex.Flowable

interface MessageDataRepository
{
    fun getMessageList(): Flowable<List<MessageModel>>
}