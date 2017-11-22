package fer.kotlin.weatherapp.domain.commands

import fer.kotlin.weatherapp.domain.datasource.DsForecastProvider
import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.domain.datasource.ForecastProvider
import fer.kotlin.weatherapp.domain.model.DsForecast

class DsRequestActualForecast(val latitude: String, val longitude: String,
                            val dsForecastProvider: DsForecastProvider = DsForecastProvider() ): Command<DsForecast> {

    override fun execute() = dsForecastProvider.requestActualForecast(latitude, longitude)
}