package app.pashmak.com.pashmak.data.model.message

import app.pashmak.com.pashmak.util.calendar.PersianDate
import app.pashmak.com.pashmak.util.calendar.TimeZones
import com.google.gson.annotations.SerializedName
import java.util.*

class MessageModel
{
    @SerializedName("id") var id: Int = 0
    @SerializedName("userId") var userId: Int = 0
    @SerializedName("userLogin") lateinit var userLogin: String
    @SerializedName("eventTimeEpoch") var timeEpoch: Long = 0
    @SerializedName("sendTime") lateinit var sendTime: String
    @SerializedName("message") lateinit var message: String
    @SerializedName("messageType") lateinit var messageType: MessageTypeEnum


    val calendar by lazy {
        Calendar.getInstance().also {
            it.timeZone = TimeZones.ASIA_TEHRAN.timeZone
            it.timeInMillis = timeEpoch
        }
    }

    val persianDate: PersianDate? by lazy { PersianDate(calendar) }

    fun getDate() = persianDate!!.shortDate
    fun getTime() = "${getHourOfDay()}:${getMinute()}"

    private fun getHourOfDay() = calendar.get(Calendar.HOUR_OF_DAY)
    private fun getMinute() = calendar.get(Calendar.MINUTE)
}