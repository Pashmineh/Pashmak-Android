package app.pashmak.com.pashmak.data.model.checkin

import com.google.gson.annotations.SerializedName

enum class CheckInType
{
    @SerializedName("MANUAL") MANUAL,
    @SerializedName("IBEACON") IBEACON
}