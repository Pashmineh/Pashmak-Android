package app.pashmak.com.pashmak.data.model.polling

import com.google.gson.annotations.SerializedName

data class PollModel
(
        @SerializedName("id") val id: Int,
        @SerializedName("anonymous") val anonymous: Boolean,
        @SerializedName("answerLimit") val answerLimit: Int,
        @SerializedName("imgsrc") val imgsrc: String,
        @SerializedName("question") val question: String,
        @SerializedName("totalVote") val totalVote: Int,
        @SerializedName("itemDTOS") val pollItemSet: List<PollItem>
)