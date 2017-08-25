package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.data.db.ForecastDb
import fer.kotlin.weatherapp.data.server.ForecastServer
import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.domain.model.ForecastList
import fer.kotlin.weatherapp.extensions.firstResult

/**
 * Created by Default on 11/08/2017.
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id: Long): Forecast = requestToSources {
        it.requestDayForecast(id)
    }

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}