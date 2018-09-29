package app.pashmak.com.pashmak.util

const val BASE_URL = "http://pashmak.kian.digital:8080"

const val BEACON_UUID = "00001803-494c-4f47-4943-544543480000"
const val BEACON_ADDRESS = "FC:7F:B6:A8:91:95"

const val IBEACON_LAYOUT_ID = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"

const val FINAL_VALID_TIME = 10

fun getAvatarUrl(phone: String) = if (phone.isNotEmpty()) "http://178.62.20.28/Photos/$phone.jpeg" else ""