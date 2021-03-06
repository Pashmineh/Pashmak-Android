package app.pashmak.com.pashmak.domain.transaction

import app.pashmak.com.pashmak.data.model.transaction.DebtModel
import app.pashmak.com.pashmak.data.repository.transaction.TransactionDataRepository
import app.pashmak.com.pashmak.domain.BaseUseCase
import app.pashmak.com.pashmak.util.ErrorUtil
import io.reactivex.Flowable
import javax.inject.Inject

class GetDebtListUseCase
@Inject constructor(
        errorUtil: ErrorUtil,
        private val transactionDataRepository: TransactionDataRepository
): BaseUseCase<List<DebtModel>>(errorUtil)
{
    override fun buildUseCaseObservable(): Flowable<List<DebtModel>> {
        return transactionDataRepository.getDebtList()
    }
}