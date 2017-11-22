package fer.kotlin.weatherapp.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Default on 22/09/2017.
 */
fun IntArray.ToDateUTC(includeUtcText: Boolean): String {

    val date = Calendar.getInstance()
    date.set(this[0], this[1], this[2], 0, 0, 0)
    val outputFmt = if (includeUtcText) {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'UTC'")
    } else SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    outputFmt.timeZone = TimeZone.getTimeZone("UTC")

    return outputFmt.format(date.time)
}

fun String.UnixToTime(): String {
    if (this != "") {
        val date = Date(this.toLong() * 1000)

        val outputFmt = SimpleDateFormat("HH:mm")
        outputFmt.timeZone = TimeZone.getTimeZone("UTC")

        return outputFmt.format(date.time)
    }
    else {
        return "[Sin datos]"
    }
}

fun IntArray.UTCtoUnix(): Long {

    val date = Calendar.getInstance()
    date.set(this[0], this[1], this[2])

    return date.timeInMillis / 1000
}