package fer.kotlin.weatherapp.data.serverinthepast

/**
 * Created by Default on 29/07/2017.
 */
data class PastForecastFirstCallResult(val descripcion: String, val estado: String, val datos: String, val metadatos: String)

data class PastForecastResult(val list: List<PastForecast>)

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
                        val horaPresMin: String)

