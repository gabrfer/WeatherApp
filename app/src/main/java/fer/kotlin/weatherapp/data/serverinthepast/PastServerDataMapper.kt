package fer.kotlin.weatherapp.data.serverinthepast

import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.domain.model.PastForecast as ModelPastForecast

/**
 * Created by Default on 11/08/2017.
 */
class PastServerDataMapper {

    fun convertToDomain(pastForecast: PastForecastResult) = with(pastForecast) {
        println("OBJETOOOOOOOOOOOOOOOOS: " + list.size)
        PastForecastList(convertPastForecastListToDomain(list))
    }

    private fun convertPastForecastListToDomain(list: List<PastForecast>): List<ModelPastForecast> {
        return list.mapIndexed { i, pastForecast ->
            convertPastForecastItemToDomain(pastForecast.copy())
        }
    }

    /* TODO: Generar icono acorde al tiempo real y no mediante el valor por defecto de prueba */
    private fun convertPastForecastItemToDomain(pastForecast: PastForecast) = with(pastForecast) {
        ModelPastForecast(fecha, indicativo, nombre, provincia, altitud, tmed, prec, tmin, horatmin, tmax, horatmax,
                          dir, velmedia, racha, horaracha, sol, presMax, horaPresMax, presMin, horaPresMin,
                          generateIconUrl("10"))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}