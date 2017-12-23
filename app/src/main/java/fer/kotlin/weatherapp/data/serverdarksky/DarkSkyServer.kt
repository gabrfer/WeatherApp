package fer.kotlin.weatherapp.data.serverdarksky

import fer.kotlin.weatherapp.data.db.DsForecastDb
import fer.kotlin.weatherapp.domain.datasource.DsForecastDataSource
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.extensions.ToDateUTC
import java.util.*

class DarkSkyServer(private val dataMapper: DarkSkyDataMapper = DarkSkyDataMapper(),
                    private val dsForecastDb: DsForecastDb = DsForecastDb()): DsForecastDataSource {

    override fun requestPastForecast(latitude: String, longitude: String, time: IntArray): DsForecast? {
        val dateWithoutTime = time.ToDateUTC(false)
        val result = DarkSkyApiCalls().requestPastForecast(latitude, longitude, dateWithoutTime)
        val converted = dataMapper.convertToDomain(result)
        dsForecastDb.saveDsForecast(converted)

        return dsForecastDb.requestPastForecast(latitude, longitude, time)
    }

    fun requestActualForecast(latitude: String, longitude: String): DsForecast {
        val result = DarkSkyApiCalls().requestActualForecast(latitude, longitude)
        return dataMapper.convertToDomain(result)
    }

}