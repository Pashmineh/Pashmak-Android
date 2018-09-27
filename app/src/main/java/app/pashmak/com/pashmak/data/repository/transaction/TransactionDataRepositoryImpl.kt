package app.pashmak.com.pashmak.data.repository.transaction

import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import io.reactivex.Flowable
import javax.inject.Inject

class TransactionDataRepositoryImpl
@Inject constructor(private val transactionDataRepositoryFactory: TransactionDataRepositoryFactory)
    : TransactionDataRepository
{
    override fun getDebtList(): Flowable<List<TransactionModel>> = transactionDataRepositoryFactory.getDebtList()

    override fun getPaymentList(): Flowable<List<TransactionModel>> = transactionDataRepositoryFactory.getPaymentList()
}