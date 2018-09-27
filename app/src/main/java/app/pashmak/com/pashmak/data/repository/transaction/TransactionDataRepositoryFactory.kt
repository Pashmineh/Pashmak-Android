package app.pashmak.com.pashmak.data.repository.transaction

import app.pashmak.com.pashmak.data.source.cloud.BaseCloudRepository
import javax.inject.Inject

class TransactionDataRepositoryFactory
@Inject constructor(private val cloudRepository: BaseCloudRepository)
{
    fun getDebtList() = cloudRepository.getDebtList()

    fun getPaymentList() = cloudRepository.getPaymentList()
}