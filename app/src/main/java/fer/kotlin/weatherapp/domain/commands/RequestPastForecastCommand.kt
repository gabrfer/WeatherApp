package fer.kotlin.weatherapp.domain.commands

import fer.kotlin.weatherapp.domain.datasource.DsForecastProvider
import fer.kotlin.weatherapp.domain.datasource.PastForecastProvider
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.domain.model.StationList
import okhttp3.OkHttpClient

/**
 * Created by Default on 15/08/2017.
 */

class DsRequestPastForecast(val latitude: String, val longitude: String, val time: IntArray,
                            val dsForecastProvider: DsForecastProvider = DsForecastProvider() ): Command<DsForecast> {

    override fun execute() = dsForecastProvider.requestPastForecast(latitude, longitude, time)
}
