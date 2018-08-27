package app.pashmak.com.pashmak.data.model.home

import app.pashmak.com.pashmak.util.calendar.PersianDate
import app.pashmak.com.pashmak.util.calendar.TimeZones
import com.google.gson.annotations.SerializedName
import java.util.*

data class Event(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("description") val description: String,
        @SerializedName("eventTimeEpoch") val timeEpoc: Long,
        @SerializedName("location") val location: String
){
    var persianDate : PersianDate? = null
    val calendar : Calendar?  = Calendar.getInstance().also {
        it.timeZone = TimeZones.ASIA_TEHRAN.timeZone
        it.timeInMillis = timeEpoc
        persianDate = PersianDate(it)
    }

    fun getMonthName() =  persianDate?.monthName ?: ""
    fun getDayOfMonth() = persianDate?.dayOfMonth ?: ""
    fun getDayOfWeek() = persianDate?.dayOfWeekName ?: ""
    fun getYear() = persianDate?.year ?: ""
    fun getHour() = calendar?.get(Calendar.HOUR_OF_DAY) ?: 0
}