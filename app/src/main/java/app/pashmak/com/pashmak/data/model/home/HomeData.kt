package app.pashmak.com.pashmak.data.model.home

import com.google.gson.annotations.SerializedName

data class HomeData(
        @SerializedName("balance") val balance: Balance,
        @SerializedName("cycle") val cycle: String,
        @SerializedName("events") val eventList: List<Event>
)