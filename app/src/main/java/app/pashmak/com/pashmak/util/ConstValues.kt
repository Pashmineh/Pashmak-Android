package app.pashmak.com.pashmak.util

const val BASE_URL = "http://178.128.195.55:8080"

fun getAvatarUrl(phone: String) = if (phone.isNotEmpty()) "http://178.62.20.28/Photos/$phone.jpeg" else ""