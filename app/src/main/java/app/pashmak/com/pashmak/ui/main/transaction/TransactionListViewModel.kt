package app.pashmak.com.pashmak.ui.main.transaction

import app.pashmak.com.pashmak.data.model.response.APIResponse
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import app.pashmak.com.pashmak.domain.transaction.GetDebtListUseCase
import app.pashmak.com.pashmak.domain.transaction.GetPaymentListUseCase
import app.pashmak.com.pashmak.ui.base.BaseViewModel
import javax.inject.Inject

class TransactionListViewModel
@Inject constructor(
        private val getDebtListUseCase: GetDebtListUseCase,
        private val getPaymentListUseCase: GetPaymentListUseCase
)
    : BaseViewModel() {

    fun getDebtList() {
        getDebtListUseCase.execute(compositeDisposable, this::onGetDebtListResponse)
        getPaymentListUseCase.execute(compositeDisposable, this::onGetDebtListResponse)
    }

    private fun onGetDebtListResponse(response: APIResponse<List<TransactionModel>>) {
    }
}