package fer.kotlin.weatherapp.data.serverdarksky

import com.google.gson.*
import java.net.URL


class DarkSkyApiCalls(private val gson: Gson = GsonBuilder().serializeNulls().create()) {

    companion object {
        private val API_KEY = "74b6a9bdaa30ecd0107cf4fd15d9b765"
        private val PAST_FORECAST_URL = "https://api.darksky.net/forecast/$API_KEY/[latitude],[longitude],[time]?exclude=currently&lang=es&units=si"
        private val ACTUAL_FORECAST_URL = "https://api.darksky.net/forecast/$API_KEY/[latitude],[longitude]?lang=es&units=si"
    }

    fun requestPastForecast(latitude: String, longitude: String, time: String): DsForecastResult
    {
        val finalURL : String = PAST_FORECAST_URL.replace("[latitude]", latitude).replace("[longitude]", longitude).replace("[time]", time)
        val dsForecastJsonStr = URL(finalURL).readText()

        return  gson.fromJson(dsForecastJsonStr, DsForecastResult::class.java)
    }

    fun requestActualForecast(latitude: String, longitude: String): DsForecastResult
    {
        val finalURL : String = ACTUAL_FORECAST_URL.replace("[latitude]", latitude).replace("[longitude]", longitude)
        val dsForecastJsonStr = URL(finalURL).readText()

        return  gson.fromJson(dsForecastJsonStr, DsForecastResult::class.java)
    }
}