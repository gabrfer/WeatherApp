package fer.kotlin.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by Default on 12/08/2017.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}