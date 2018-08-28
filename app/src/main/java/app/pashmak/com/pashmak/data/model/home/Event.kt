package app.pashmak.com.pashmak.data.model.home

import app.pashmak.com.pashmak.util.calendar.PersianDate
import app.pashmak.com.pashmak.util.calendar.TimeZones
import com.google.gson.annotations.SerializedName
import java.util.*

class Event {

    @SerializedName("id") var id: Int = 0
    @SerializedName("name") lateinit var name: String
    @SerializedName("description") lateinit var description: String
    @SerializedName("eventTimeEpoch") var timeEpoc: Long = 0
    @SerializedName("location") lateinit var location: String

    val calendar by lazy {
        Calendar.getInstance().also {
            it.timeZone = TimeZones.ASIA_TEHRAN.timeZone
            it.timeInMillis = timeEpoc
        }
    }

    val persianDate: PersianDate? by lazy { PersianDate(calendar) }

    fun getMonthName() = persianDate?.monthName ?: ""
    fun getDayOfMonth() = persianDate?.dayOfMonth ?: ""
    fun getDayOfWeek() = persianDate?.dayOfWeekName ?: ""
    fun getYear() = persianDate?.year ?: ""
    fun getHour() = "${calendar?.get(Calendar.HOUR_OF_DAY) ?: 0}:${calendar?.get(Calendar.MINUTE) ?: 0}"
}