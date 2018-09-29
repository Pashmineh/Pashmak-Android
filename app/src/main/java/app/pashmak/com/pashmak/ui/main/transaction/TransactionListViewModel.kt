package app.pashmak.com.pashmak.ui.main.transaction

import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.response.ErrorResponse
import app.pashmak.com.pashmak.data.model.response.SuccessResponse
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import app.pashmak.com.pashmak.domain.transaction.GetAllTransactionUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import app.pashmak.com.pashmak.util.livedata.NonNullLiveData
import javax.inject.Inject

class TransactionListViewModel
@Inject constructor(
        private val getAllTransactionUseCase: GetAllTransactionUseCase
)
    : BaseViewModel() {

    val isLoading: NonNullLiveData<Boolean> = NonNullLiveData(false)
    val transactionListLiveData: MutableLiveData<List<TransactionModel>> = MutableLiveData()

    fun getTransactionList() {
        isLoading.value = true
        getAllTransactionUseCase.execute(compositeDisposable, this::onTransactionListResponse)
    }

    private fun onTransactionListResponse(response: APIResponse<List<TransactionModel>>)
    {
        isLoading.value = false
        when(response){
            is SuccessResponse -> {
                //TODO sort it with Epoc time when its ready
                val sortedList = response.value.sortedBy { it.paymentTime }
                transactionListLiveData.value =  sortedList
            }
            is ErrorResponse -> {}
        }
    }
}