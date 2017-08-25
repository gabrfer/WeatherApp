package fer.kotlin.weatherapp.domain.model

/**
 * Created by Default on 30/07/2017.
 */
data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast:List<Forecast>) {
    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)


data class PastForecastList(val pastForecastList:List<PastForecast>) {
    val size: Int
        get() = pastForecastList.size

    operator fun get(position: Int) = pastForecastList[position]
}

data class PastForecast(val fecha: String,
                        val indicativo: String,
                        val nombre: String,
                        val provincia: String,
                        val altitud: String,
                        val tmed: String,
                        val prec: String,
                        val tmin: String,
                        val horatmin: String,
                        val tmax: String,
                        val horatmax: String,
                        val dir: String,
                        val velmedia: String,
                        val racha: String,
                        val horaracha: String,
                        val sol: String,
                        val presMax: String,
                        val horaPresMax: String,
                        val presMin: String,
                        val horaPresMin: String,
                        val iconUrl: String)