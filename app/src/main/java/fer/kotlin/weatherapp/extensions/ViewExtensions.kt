package fer.kotlin.weatherapp.extensions

/**
 * Created by Default on 10/08/2017.
 */
import android.content.Context
import android.view.View
import android.widget.TextView

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)