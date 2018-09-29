package app.pashmak.com.pashmak.data.model.transaction

import app.pashmak.com.pashmak.R


class DebtModel: TransactionModel()
{
    override fun isPayment(): Boolean = false

    override fun getIconRes(): Int {
        return when(reason){
            ReasonTypeEnum.TAKHIR -> R.drawable.vector_debt_takhir
            ReasonTypeEnum.SHIRINI -> R.drawable.vector_debt_shirini
            ReasonTypeEnum.JALASE -> R.drawable.vector_debt_jalase
        }
    }

    override fun getCaptionRes(): Int = reason.captionRes
}