package fer.kotlin.weatherapp.domain.model

/**
 * Created by Default on 30/07/2017.
 */
data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast:List<Forecast>) {
    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast(val date: Long, val description: String, val high: Int, val low: Int, val iconURL: String)