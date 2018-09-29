package app.pashmak.com.pashmak.data.repository.transaction

import app.pashmak.com.pashmak.data.model.transaction.DebtModel
import app.pashmak.com.pashmak.data.model.transaction.PaymentModel
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class TransactionDataRepositoryFactory
@Inject constructor(private val cloudRepository: BaseCloudRepository)
{
    fun getDebtList() = cloudRepository.getDebtList()

    fun getPaymentList() = cloudRepository.getPaymentList()

    fun getAllTransactions() =
            Flowable.zip(
                    cloudRepository.getDebtList(),
                    cloudRepository.getPaymentList(),
                    BiFunction<List<DebtModel>, List<PaymentModel>, List<TransactionModel>>{
                        debtList, paymentList ->
                            val mutableList: MutableList<TransactionModel> = mutableListOf()
                            mutableList.addAll(debtList)
                            mutableList.addAll(paymentList)
                            mutableList.toList()
                    }
            )
}