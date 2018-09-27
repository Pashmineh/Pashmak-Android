package app.pashmak.com.pashmak.data.model.transaction

import com.google.gson.annotations.SerializedName

data class TransactionModel
(
        @SerializedName("id") val id: Int,
        @SerializedName("userId") val userId: Int,
        @SerializedName("userLogin") val userLogin: String,
        @SerializedName("paymentTime") val paymentTime: String,
        @SerializedName("reason") val reason: String,
        @SerializedName("amount") val amount: Int
)