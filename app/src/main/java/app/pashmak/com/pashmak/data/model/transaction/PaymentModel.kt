package app.pashmak.com.pashmak.data.model.transaction

import app.pashmak.com.pashmak.R


class PaymentModel: TransactionModel()
{
    override fun isPayment(): Boolean = true
    override fun getIconRes(): Int = R.drawable.vector_payment
    override fun getCaptionRes(): Int = R.string.payment_caption
}