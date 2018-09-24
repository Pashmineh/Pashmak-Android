package app.pashmak.com.pashmak.ui.main.polling

import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.data.model.polling.PollItem
import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.domain.polling.GetPollsUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import javax.inject.Inject

class PollingViewModel
@Inject constructor(
        private val getPollsUseCase: GetPollsUseCase
)
    : BaseViewModel()
{

    val pollListLiveData: MutableLiveData<List<PollModel>> = MutableLiveData()

    fun getPolls(){
        getPollsUseCase.execute(compositeDisposable, this::onGetPollResponse)
    }

    fun onVoteClick(item: PollItem){
        if(item.voted){}
        else{}
    }

    private fun onGetPollResponse(response: APIResponse<List<PollModel>>){
        when(response){
            is SuccessResponse -> {
                pollListLiveData.value = response.value
            }
            is ErrorResponse -> {}
        }
    }
}