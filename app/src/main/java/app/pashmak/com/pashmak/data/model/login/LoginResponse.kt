package app.pashmak.com.pashmak.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("token") val token: String,
        @SerializedName("name") val firstName: String,
        @SerializedName("lastName") val lastName: String,
        @SerializedName("avatar") val avatar: String
)