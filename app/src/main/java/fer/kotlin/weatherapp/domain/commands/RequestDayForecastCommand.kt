package fer.kotlin.weatherapp.domain.commands

import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.domain.datasource.ForecastProvider

/**
 * Created by Default on 12/08/2017.
 */
class RequestDayForecastCommand(val id: Long, val forecastProvider: ForecastProvider = ForecastProvider()) : Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
    
}