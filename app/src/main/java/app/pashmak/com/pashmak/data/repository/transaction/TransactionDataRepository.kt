package app.pashmak.com.pashmak.data.repository.transaction

import app.pashmak.com.pashmak.data.model.transaction.TransactionModel
import io.reactivex.Flowable

interface TransactionDataRepository
{
    fun getDebtList(): Flowable<List<TransactionModel>>

    fun getPaymentList(): Flowable<List<TransactionModel>>
}