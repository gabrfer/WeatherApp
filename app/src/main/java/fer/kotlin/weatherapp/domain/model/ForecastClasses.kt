package fer.kotlin.weatherapp.domain.model

/**
 * Created by Default on 30/07/2017.
 */
data class ForecastList(val city: String, val country: String, val dailyForecast:List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int, val iconURL: String)