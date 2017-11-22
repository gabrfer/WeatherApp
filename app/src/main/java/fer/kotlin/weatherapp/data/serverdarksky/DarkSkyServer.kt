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
        val result = DarkSkyApiCalls().RequestPastForecast(latitude, longitude, dateWithoutTime)
        val converted = dataMapper.convertToDomain(result, dateWithoutTime)
        dsForecastDb.saveDsForecast(converted)

        return dsForecastDb.requestPastForecast(latitude, longitude, time)
    }

    fun requestActualForecast(latitude: String, longitude: String): DsForecast {
        val calendar = Calendar.getInstance()
        val time: IntArray = intArrayOf(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        val dateWithoutTime = time.ToDateUTC(false)

        val result = DarkSkyApiCalls().RequestActualForecast(latitude, longitude)
        return dataMapper.convertToDomain(result, dateWithoutTime)
    }

}