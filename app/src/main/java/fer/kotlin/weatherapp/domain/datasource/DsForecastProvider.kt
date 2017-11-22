package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.data.db.DsForecastDb
import fer.kotlin.weatherapp.data.dbHerriak.HerriakDb
import fer.kotlin.weatherapp.data.serverdarksky.DarkSkyServer
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.Herria
import fer.kotlin.weatherapp.domain.model.HerriaKFilter
import fer.kotlin.weatherapp.extensions.firstResult


class DsForecastProvider(private val sources: List<DsForecastDataSource> = DsForecastProvider.SOURCES) {

    companion object {
        val SOURCES = listOf(DsForecastDb(), DarkSkyServer())
        val DB_HERRIAK = HerriakDb()
    }

    fun requestHerriak(filter: HerriaKFilter): List<Herria> = DB_HERRIAK.requestHerriak(filter)

    fun requestPastForecast(latitude: String, longitude: String, time: IntArray): DsForecast = requestToSources {
        it.requestPastForecast(latitude, longitude, time)
    }

    fun requestActualForecast(latitude: String, longitude: String): DsForecast =
            DarkSkyServer().requestActualForecast(latitude, longitude)

    private fun <T : Any> requestToSources(f: (DsForecastDataSource) -> T?): T = sources.firstResult { f(it) }
}