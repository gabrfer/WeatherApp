package fer.kotlin.weatherapp.domain.commands

import fer.kotlin.weatherapp.domain.model.ForecastList
import fer.kotlin.weatherapp.domain.datasource.ForecastProvider
/**
 * Created by Default on 30/07/2017.
 */
class RequestForecastCommand(val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}