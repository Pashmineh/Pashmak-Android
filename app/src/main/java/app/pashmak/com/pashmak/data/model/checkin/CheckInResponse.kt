package app.pashmak.com.pashmak.data.model.checkin

import app.pashmak.com.pashmak.R
import app.pashmak.com.pashmak.util.FINAL_VALID_TIME
import app.pashmak.com.pashmak.util.calendar.PersianDate
import app.pashmak.com.pashmak.util.calendar.TimeZones
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.math.min

class CheckInResponse{

    @SerializedName("id") var id: Int = 0
    @SerializedName("userId") var userId: Int = 0
    @SerializedName("checkinTime") lateinit var checkInTimeText: String
    @SerializedName("checkinTimeEpoch") var checkInTimeValue: Long = 0
    @SerializedName("checkinType") lateinit var checkInType: CheckInType
    @SerializedName("message") lateinit var message: String
    @SerializedName("userLogin") lateinit var userLogin: String

    private val calendar by lazy {
        Calendar.getInstance().also {
            it.timeZone = TimeZones.ASIA_TEHRAN.timeZone
            it.timeInMillis = checkInTimeValue
        }
    }

    private val persianDate: PersianDate? by lazy { PersianDate(calendar) }


    private fun getHourOfDay() = calendar.get(Calendar.HOUR_OF_DAY)

    private fun getMinute() = calendar.get(Calendar.MINUTE)

    fun getCheckInColorRes(): Int {
        return if (getHourOfDay() > FINAL_VALID_TIME || (getHourOfDay() == 10 && getMinute() > 0))
            R.color.Ruddy
        else
            R.color.Kelly_green
    }

    fun getCheckInIconRes(): Int {
        return if (getHourOfDay() > FINAL_VALID_TIME || (getHourOfDay() == 10 && getMinute() > 0) )
            R.drawable.vector_late_checkin
        else
            R.drawable.vector_ontime_checkin
    }

    fun getCheckInHour(): String{
        val hour = if(getHourOfDay() < 10) "0${getHourOfDay()}" else getHourOfDay().toString()
        val minute = if(getMinute() < 10) "0${getMinute()}" else getMinute().toString()
        return "$hour:$minute"
    }

    fun getCheckInDate() = "${persianDate!!.dayOfWeekName} ${persianDate!!.dayOfMonth} ${persianDate!!.monthName} ${persianDate!!.year}"
}