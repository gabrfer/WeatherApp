package fer.kotlin.weatherapp.data.db

import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.domain.model.ForecastList
import fer.kotlin.weatherapp.domain.model.Station
import fer.kotlin.weatherapp.domain.model.StationList
import java.util.*

/**
 * Created by Default on 10/08/2017.
 */

class DbDataMapper {

    /* Forecast */
    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id, date, description, high, low, iconUrl)
    }

    /* Stations */
    fun convertStationListFromDomain(list: StationList) = with(list)  {
        stationsList.map { convertStationFromDomain(it)}
    }

    fun convertStationFromDomain(station: Station) = with(station) {
        StationDBO(latitud, provincia, altitud, indicativo, nombre, indsinop, longitud)
    }

    fun convertStationListToDomain(list: List<StationDBO>) : List<Station> {
        val stationList = list.map { Station(it._id, it.latitud, it.provincia, it.altitud, it.indicativo, it.nombre, it.indsinop, it.longitud)}

        return stationList
    }


}