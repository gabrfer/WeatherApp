package fer.kotlin.weatherapp.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso
import fer.kotlin.weatherapp.R


fun ImageView.loadForecastIconUrl(iconText: String, ctx: Context) {
    Picasso.with(ctx).load(getDrawableForecast(iconText)).into(this)
}

fun ImageView.loadRainTypeIconUrl(iconText: String, ctx: Context) {
        Picasso.with(ctx).load(getDrawableRain(iconText)).into(this)
}

fun ImageView.loadMoonPhaseIconUrl(phase: Double, ctx: Context) {
    val resourceStr = getDrawableMoonPhase(phase)
    val resourceId = resources.getIdentifier(resourceStr, "mipmap", context.packageName)
    Picasso.with(ctx).load(resourceId).into(this)
}

fun getDrawableForecast(name: String) =

    when (name) {
        "clear-day" -> R.mipmap.clear_day_white
        "clear-night" -> R.mipmap.clear_night_white
        "rain" -> R.mipmap.rain_white
        "snow" -> R.mipmap.snow_white
        "sleet" -> R.mipmap.sleet_white
        "wind" -> R.mipmap.wind_white
        "fog" -> R.mipmap.fog_white
        "cloudy" -> R.mipmap.cloudy_white
        "partly-cloudy-day" -> R.mipmap.partly_cloudy_day_white
        "partly-cloudy-night" -> R.mipmap.partly_cloudy_night_white
        "hail" -> R.mipmap.hail_white
        "thunderstorm" -> R.mipmap.thunderstorm_white
        "tornado" -> R.mipmap.tornado_white
        else -> R.mipmap.na_white
    }

fun getDrawableRain(name: String) =
        when (name) {
                "rain" -> R.mipmap.rain
                "snow" -> R.mipmap.snow
                "sleet" -> R.mipmap.sleet
                else -> R.mipmap.na
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

fun getDrawableMoonPhase(phase: Double): String {

    val result: String

    val moonPhaseRanges = arrayListOf(0.0, 3.23, 6.46, 9.69, 12.92, 16.15, 19.38, 22.61, 25.84, 29.07, 32.3, 35.53,
            38.76, 41.99, 45.22, 48.45, 51.68, 54.91, 58.14, 61.37, 64.6, 67.83, 71.06, 74.29, 77.52, 80.75, 83.98, 87.21,
            90.44, 93.67, 96.90)

    val phaseStr:String = moonPhaseRanges.last { x -> x <= phase*100 }.toString()
    result = "moonphase_" + phaseStr.replace(".", "_")

    return result
}

fun getDrawableMoonPhaseSimple(name: Double) =
        when (name) {
            0.0 -> R.mipmap.luna_llena
            in 0.01 .. 0.24 -> R.mipmap.luna_gibada_creciente
            0.25 -> R.mipmap.luna_cuarto_creciente
            in 0.26 .. 0.49 -> R.mipmap.luna_nueva_visible
            0.5 -> R.mipmap.luna_nueva
            in 0.51 .. 0.74 -> R.mipmap.luna_menguante
            0.75 -> R.mipmap.luna_cuarto_menguante
            in 0.76 .. 1.00 -> R.mipmap.luna_gibada_menguante

            else -> R.mipmap.na
        }