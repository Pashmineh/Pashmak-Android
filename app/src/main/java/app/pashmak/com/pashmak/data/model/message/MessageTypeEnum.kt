package app.pashmak.com.pashmak.data.model.message

import com.google.gson.annotations.SerializedName

enum class MessageTypeEnum
{
    @SerializedName("PUSH") PUSH,
    @SerializedName("SMS") SMS,
    @SerializedName("EMAIL") EMAIL
}