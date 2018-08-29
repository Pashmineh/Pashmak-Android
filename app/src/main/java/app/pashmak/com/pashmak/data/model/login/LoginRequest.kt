package app.pashmak.com.pashmak.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("username") val phone: String,
        @SerializedName("password") val password: String,
        @SerializedName("token") val firebaseToken: String,
        @SerializedName("rememberMe") val rememberMe: Boolean = true,
        @SerializedName("platform") val platform: String = "ANDROID"
)