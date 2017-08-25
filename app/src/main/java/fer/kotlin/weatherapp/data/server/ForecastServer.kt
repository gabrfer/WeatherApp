package fer.kotlin.weatherapp.data.server

import fer.kotlin.weatherapp.data.db.ForecastDb
import fer.kotlin.weatherapp.domain.datasource.ForecastDataSource
import fer.kotlin.weatherapp.domain.model.ForecastList

/**
 * Created by Default on 11/08/2017.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}