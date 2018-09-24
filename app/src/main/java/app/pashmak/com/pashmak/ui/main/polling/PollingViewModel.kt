package app.pashmak.com.pashmak.ui.main.polling

import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.data.model.polling.PollItem
import app.pashmak.com.pashmak.data.model.polling.PollModel
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.domain.polling.GetPollsUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class PollingViewModel
@Inject constructor(
        private val getPollsUseCase: GetPollsUseCase
)
    : BaseViewModel()
{

    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val pollListLiveData: MutableLiveData<List<PollModel>> = MutableLiveData()

    fun getPolls(){
        isLoading.value = true
        getPollsUseCase.execute(compositeDisposable, this::onGetPollResponse)
    }

    fun onVoteClick(item: PollItem){
        if(item.voted){}
        else{}
    }

    private fun onGetPollResponse(response: APIResponse<List<PollModel>>){

        isLoading.value = false

        when(response){
            is SuccessResponse -> {
                val itemList = response.value
                for(model in itemList){
                    model.myVoteCount = 0
                    for(item in model.pollItemSet){
                        if(item.voted) model.myVoteCount++
                    }
                }
                pollListLiveData.value = response.value
            }
            is ErrorResponse -> {}
        }
    }
}