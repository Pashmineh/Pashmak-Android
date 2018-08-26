package app.pashmak.com.pashmak.data.model.home

import com.google.gson.annotations.SerializedName

data class Balance(
        @SerializedName("balance") val balance: Double,
        @SerializedName("totalPaid") val paid: Double
)