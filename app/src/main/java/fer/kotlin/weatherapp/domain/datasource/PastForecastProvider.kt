package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.data.db.ForecastDb
import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.data.serverinthepast.PastForecastServer
import fer.kotlin.weatherapp.domain.model.PastForecast
import fer.kotlin.weatherapp.domain.model.StationList
import fer.kotlin.weatherapp.extensions.firstResult
import okhttp3.OkHttpClient

/**
 * Created by Default on 15/08/2017.
 */
class PastForecastProvider(val okHttpSslClient: OkHttpClient, val sources: List<PastForecastDataSource> = listOf(ForecastDb(), PastForecastServer(okHttpSslClient))) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        //val SOURCES = listOf(ForecastDb(), PastForecastServer(okHttpSslClient))
    }

    fun requestByDateStation(station: String, dateFrom: String, dateTo: String): PastForecastList {
        val res = PastForecastServer(okHttpSslClient).requestForecastByDateStation(station, dateFrom, dateTo)

        return res ?: PastForecastList(emptyList())
    }

    fun requestStationsNew(): StationList {
        var res:StationList
        res = ForecastDb().requestStations()
        if (res != null && res.size > 0) res
        else {
            res = PastForecastServer(okHttpSslClient).requestStations()!!
        }

        return res
    }

    fun requestStations(): StationList = requestToSources {

        val res = it.requestStations()
        if (res != null && res.size > 0) res else null
    }

    private fun <T : Any> requestToSources(f: (PastForecastDataSource) -> T?): T = sources.firstResult { f(it) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}