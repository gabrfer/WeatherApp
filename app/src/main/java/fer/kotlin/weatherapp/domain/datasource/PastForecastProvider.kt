package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.data.serverinthepast.PastForecastServer
import fer.kotlin.weatherapp.extensions.firstResult

/**
 * Created by Default on 15/08/2017.
 */
class PastForecastProvider(val sources: List<PastForecastDataSource> = PastForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(PastForecastServer())
    }

    fun requestByDateStation(station: String, dateFrom: String, dateTo: String): PastForecastList = requestToSources {
        val res = it.requestForecastByDateStation(station, dateFrom, dateTo)
        if (res != null && res.size >= 0) res else null
    }


    private fun <T : Any> requestToSources(f: (PastForecastDataSource) -> T?): T = sources.firstResult { f(it) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}