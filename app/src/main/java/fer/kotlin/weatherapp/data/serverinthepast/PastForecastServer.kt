package fer.kotlin.weatherapp.data.serverinthepast


import fer.kotlin.weatherapp.data.db.ForecastDb
import fer.kotlin.weatherapp.domain.datasource.PastForecastDataSource
import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.domain.model.StationList
import okhttp3.OkHttpClient


class PastForecastServer(private val okHttpSslClient: OkHttpClient, private val dataMapper: PastServerDataMapper = PastServerDataMapper(),
                         private val forecastDb: ForecastDb = ForecastDb()) : PastForecastDataSource {

    override fun requestForecastByDateStation(station: String, dateFrom: String, dateTo: String): PastForecastList? {
        val result = PastForecastByDateStation(station, dateFrom, dateTo, okHttpSslClient).execute()

        return dataMapper.convertToDomain(result)
    }

    override fun requestStations(): StationList? {
        val result = PastForecastStations(okHttpSslClient).execute()
        val converted = dataMapper.convertStationsToDomain(result)
        forecastDb.saveStations(converted)
        return forecastDb.requestStations()
    }
}