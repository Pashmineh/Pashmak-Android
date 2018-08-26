package app.pashmak.com.pashmak.data.model.home

import com.google.gson.annotations.SerializedName

data class Event(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("eventTime") val time: String,
        @SerializedName("location") val location: String
)