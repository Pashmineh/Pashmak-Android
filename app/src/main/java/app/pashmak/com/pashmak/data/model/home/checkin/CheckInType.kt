package app.pashmak.com.pashmak.data.model.home.checkin

import com.google.gson.annotations.SerializedName

enum class CheckInType
{
    @SerializedName("MANUAL") MANUAL,
    @SerializedName("IBEACON") IBEACON
}