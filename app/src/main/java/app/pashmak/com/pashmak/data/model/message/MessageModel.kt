package app.pashmak.com.pashmak.data.model.message

import com.google.gson.annotations.SerializedName

data class MessageModel
(
        @SerializedName("id") val id: Int,
        @SerializedName("userId") val userId: Int,
        @SerializedName("userLogin") val userLogin: String,
        @SerializedName("eventTimeEpoch") val timeEpoch: Long,
        @SerializedName("sendTime") val sendTime: String,
        @SerializedName("message") val message: String,
        @SerializedName("messageType") val messageType: MessageTypeEnum
)