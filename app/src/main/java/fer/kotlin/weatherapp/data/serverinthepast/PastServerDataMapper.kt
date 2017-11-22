package fer.kotlin.weatherapp.data.serverinthepast

import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.domain.model.PastForecast as ModelPastForecast
import fer.kotlin.weatherapp.domain.model.StationList
import fer.kotlin.weatherapp.domain.model.Station as ModelStation


/**
 * Created by Default on 11/08/2017.
 */
class PastServerDataMapper {

    /* Forecast */
    fun convertToDomain(pastForecast: PastForecastResult) = with(pastForecast) {
        PastForecastList(convertPastForecastListToDomain(list))
    }

    private fun convertPastForecastListToDomain(list: List<PastForecast>): List<ModelPastForecast> {
        return list.mapIndexed { i, pastForecast ->
            convertPastForecastItemToDomain(pastForecast.copy())
        }
    }

    /* TODO: Generar icono acorde al tiempo real y no mediante el valor por defecto de prueba */
    private fun convertPastForecastItemToDomain(pastForecast: PastForecast) = with(pastForecast) {
        ModelPastForecast(-1, "", fecha, indicativo, nombre, provincia, altitud, tmed, prec, tmin, horatmin, tmax, horatmax,
                          dir, velmedia, racha, horaracha, sol, presMax, horaPresMax, presMin, horaPresMin,
                          generateIconUrl("10d"))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"

    /* Stations */
    fun convertStationsToDomain(stationList: StationsResult) = with(stationList) {
        StationList(convertStationListToDomain(list))
    }

    private fun convertStationListToDomain(list: List<Station>): List<ModelStation> {
        return list.mapIndexed { i, stationsList ->
            convertStationToDomain(stationsList.copy())
        }
    }

    private fun convertStationToDomain(station: Station) = with(station) {
        ModelStation(id, latitud, provincia, altitud, indicativo, nombre, indsinop, longitud)
    }
}