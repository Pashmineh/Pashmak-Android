package app.pashmak.com.pashmak.data.repository.transaction

import javax.inject.Inject

class TransactionDataRepositoryImpl
@Inject constructor(private val transactionDataRepositoryFactory: TransactionDataRepositoryFactory)
    : TransactionDataRepository
{
}