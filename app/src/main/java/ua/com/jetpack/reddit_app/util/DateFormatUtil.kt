package ua.com.jetpack.reddit_app.util

import android.text.format.DateFormat
import ua.com.jetpack.reddit_app.common.Constants
import java.lang.Exception
import android.text.format.DateUtils
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


object DateFormatUtil {
    fun convertUtcToTimeAgo(mTimestamp: Long): String {
        val dateCreate = DateFormat.format(Constants.FORMAT_TIME_UA, mTimestamp * 1000L).toString()
        var conversionTime = ""
        try {

            val sdf = SimpleDateFormat(Constants.FORMAT_TIME_UA)

            val datetime = Calendar.getInstance()
            var date2 = sdf.format(datetime.time)

            val dateObj1 = sdf.parse(dateCreate)
            val dateObj2 = sdf.parse(date2.toString())
            val diff = dateObj2.time - dateObj1.time

            val diffDays = diff / (24 * 60 * 60 * 1000)
            val diffhours = diff / (60 * 60 * 1000)
            val diffmin = diff / (60 * 1000)
            val diffsec = diff / 1000

            when {
                diffDays > 1 -> conversionTime += "$diffDays days "
                diffhours > 1 -> conversionTime += (diffhours - diffDays * 24).toString() + " hours "
                diffmin > 1 -> conversionTime += (diffmin - diffhours * 60).toString() + " min "
                diffsec > 1 -> conversionTime += (diffsec - diffmin * 60).toString() + " sec "
            }
        } catch (ex: Exception) {
            Log.e("formatTimeAgo", ex.toString())
        }
        if (conversionTime != "") conversionTime += "ago"

        return conversionTime
    }
}