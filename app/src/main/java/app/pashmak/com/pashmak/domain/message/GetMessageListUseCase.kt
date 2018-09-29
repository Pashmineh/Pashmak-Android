package app.pashmak.com.pashmak.domain.message

import app.pashmak.com.pashmak.data.model.message.MessageModel
import app.pashmak.com.pashmak.data.repository.message.MessageDataRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class GetMessageListUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val messageDataRepository: MessageDataRepository
)
    :BaseUseCase<List<MessageModel>>(errorUtil)
{
    override fun buildUseCaseObservable(): Flowable<List<MessageModel>> {
        return messageDataRepository.getMessageList()
    }
}