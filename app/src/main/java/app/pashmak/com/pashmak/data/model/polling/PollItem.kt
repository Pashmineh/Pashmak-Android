package app.pashmak.com.pashmak.data.model.polling

import com.google.gson.annotations.SerializedName

data class PollItem(
        @SerializedName("id") val id: Int,
        @SerializedName("imgsrc") val imgsrc: String,
        @SerializedName("number") val number: Int,
        @SerializedName("title") val title: String,
        @SerializedName("voted") val voted: Boolean

)