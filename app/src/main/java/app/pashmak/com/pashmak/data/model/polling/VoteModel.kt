package app.pashmak.com.pashmak.data.model.polling

import com.google.gson.annotations.SerializedName

data class VoteModel
(
        @SerializedName("item") val item: Int,
        @SerializedName("poll") val poll: Int
)