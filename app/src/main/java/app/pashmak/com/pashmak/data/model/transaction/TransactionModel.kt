package app.pashmak.com.pashmak.data.model.transaction

import com.google.gson.annotations.SerializedName
import java.text.DecimalFormat

abstract class TransactionModel
{
    @SerializedName("id") var id: Int = 0
    @SerializedName("userId") var userId: Int = 0
    @SerializedName("userLogin") lateinit var userLogin: String
    @SerializedName("paymentTime") var paymentTime: String? = null
    @SerializedName("reason") lateinit var reason: ReasonTypeEnum
    @SerializedName("amount") var amount: Int = 0

    abstract fun isPayment(): Boolean
    abstract fun getIconRes(): Int
    abstract fun getCaptionRes(): Int
    fun getFormattedAmount() = DecimalFormat().format(amount)
}