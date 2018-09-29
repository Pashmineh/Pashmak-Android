package app.pashmak.com.pashmak.ui.main.messages

import app.pashmak.com.pashmak.data.model.message.MessageModel
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.domain.message.GetMessageListUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class MessageListViewModel
@Inject constructor(
        private val getMessageListUseCase: GetMessageListUseCase
): BaseViewModel()
{
    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)

    fun getMessageList(){
        isLoading.value = true
        getMessageListUseCase.execute(compositeDisposable, this::onMessageListResponse)
    }

    fun onMessageListResponse(response: APIResponse<List<MessageModel>>){
        isLoading.value = false
        when(response){
            is SuccessResponse -> {}
            is ErrorResponse -> {}
        }
    }
}