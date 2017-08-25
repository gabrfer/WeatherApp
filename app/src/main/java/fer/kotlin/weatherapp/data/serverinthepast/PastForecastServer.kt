package fer.kotlin.weatherapp.data.serverinthepast

import fer.kotlin.weatherapp.data.db.ForecastDb
import fer.kotlin.weatherapp.domain.datasource.ForecastDataSource
import fer.kotlin.weatherapp.domain.datasource.PastForecastDataSource
import fer.kotlin.weatherapp.domain.model.ForecastList
import fer.kotlin.weatherapp.domain.model.PastForecastList

/**
 * Created by Default on 15/08/2017.
 */
class PastForecastServer(val dataMapper: PastServerDataMapper = PastServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : PastForecastDataSource {

    override fun requestForecastByDateStation(station: String, dateFrom: String, dateTo: String): PastForecastList? {
        val result = PastForecastByDateStation(station, dateFrom, dateTo).execute()
        val converted = dataMapper.convertToDomain(result)

        return converted
    }

}