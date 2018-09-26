package app.pashmak.com.pashmak.data.model.checkin

import com.google.gson.annotations.SerializedName

data class CheckInResponse(
        @SerializedName("id") val id: Int,
        @SerializedName("userId") val userId: Int,
        @SerializedName("checkinTime") val checkInTimeText: String,
        @SerializedName("checkinTimeEpoch") val checkInTimeValue: Long,
        @SerializedName("checkinType") val checkInType: CheckInType,
        @SerializedName("message") val message: String,
        @SerializedName("userLogin") val userLogin: String

        )