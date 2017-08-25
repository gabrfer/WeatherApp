package fer.kotlin.weatherapp.extensions

/**
 * Created by Default on 12/08/2017.
 */
import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)