package app.pashmak.com.pashmak.data.model.polling

import com.google.gson.annotations.SerializedName

data class VoteModel
(
        @SerializedName("item") val itemId: Int,
        @SerializedName("poll") val pollId: Int
)