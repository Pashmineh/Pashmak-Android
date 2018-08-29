package app.pashmak.com.pashmak.data.model.home.checkin

import com.google.gson.annotations.SerializedName

data class CheckInResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("checkinTime") val dateTime: String,
        @SerializedName("checkinTimeEpoch") val timeEpoch: Long,
        @SerializedName("checkinType") val checkinType: CheckInType,
        @SerializedName("message") val message: String,
        @SerializedName("userId") val userId: Int,
        @SerializedName("userLogin") val userLogin: String

        )