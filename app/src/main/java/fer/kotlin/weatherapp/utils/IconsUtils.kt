package fer.kotlin.weatherapp.utils

import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import fer.kotlin.weatherapp.R
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import com.fasterxml.jackson.databind.util.ClassUtil.getPackageName


/**
 * Created by Default on 15/10/2017.
 */
fun ImageView.loadForecastIconUrl(iconText: String, ctx: Context) {
    Picasso.with(ctx).load(getDrawableForecast(iconText)).into(this)
}

fun ImageView.loadRainTypeIconUrl(iconText: String, ctx: Context) {
        Picasso.with(ctx).load(getDrawableRain(iconText)).into(this)
}

fun ImageView.loadMoonPhaseIconUrl(phase: Double, ctx: Context) {
    Picasso.with(ctx).load(getDrawableMoonPhase(phase)).into(this)
}

fun getDrawableForecast(name: String) =

    when (name) {
        "clear-day" -> R.drawable.clear_day
        "clear-night" -> R.drawable.clear_night
        "rain" -> R.drawable.rain
        "snow" -> R.drawable.snow
        "sleet" -> R.drawable.sleet
        "wind" -> R.drawable.wind
        "fog" -> R.drawable.fog
        "cloudy" -> R.drawable.cloudy
        "partly-cloudy-day" -> R.drawable.partly_cloudy_day
        "partly-cloudy-night" -> R.drawable.partly_cloudy_night
        "hail" -> R.drawable.hail
        "thunderstorm" -> R.drawable.thunderstorm
        "tornado" -> R.drawable.tornado
        else -> R.drawable.na
    }

fun getDrawableRain(name: String) =
        when (name) {
                "rain" -> R.drawable.rain
                "snow" -> R.drawable.snow
                "sleet" -> R.drawable.sleet
                else -> R.drawable.na
        }

fun getPrecipType(name: String) =
        when (name) {
            "rain" -> "LLUVIA"
            "snow" -> "NIEVE"
            "sleet" -> "GRANIZO"
            else -> "SIN LLUVIA"
        }

fun getUvIndexColor(uvIndex: Int) =
        when (uvIndex) {
            in 0..2 -> android.R.color.holo_green_dark
            in 3..5 -> R.color.yellow
            in 6..7 -> android.R.color.holo_orange_dark
            in 8..10 -> android.R.color.holo_red_dark
            in 11..Int.MAX_VALUE -> android.R.color.holo_purple
            else -> android.R.color.transparent
        }

fun getDrawableMoonPhase(name: Double) =
        when (name) {
            0.0 -> R.drawable.luna_llena
            in 0.01 .. 0.24 -> R.drawable.luna_gibada_creciente
            0.25 -> R.drawable.luna_cuarto_creciente
            in 0.26 .. 0.49 -> R.drawable.luna_nueva_visible
            0.5 -> R.drawable.luna_nueva
            in 0.51 .. 0.74 -> R.drawable.luna_menguante
            0.75 -> R.drawable.luna_cuarto_menguante
            in 0.76 .. 1.00 -> R.drawable.luna_gibada_menguante

            else -> R.drawable.na
        }