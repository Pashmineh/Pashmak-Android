package app.pashmak.com.pashmak.data.repository.transaction

import app.pashmak.com.pashmak.data.model.transaction.DebtModel
import app.pashmak.com.pashmak.data.model.transaction.PaymentModel
import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import io.reactivex.Flowable

interface TransactionDataRepository
{
    fun getDebtList(): Flowable<List<DebtModel>>

    fun getPaymentList(): Flowable<List<PaymentModel>>

    fun getAllTransactions(): Flowable<List<TransactionModel>>
}