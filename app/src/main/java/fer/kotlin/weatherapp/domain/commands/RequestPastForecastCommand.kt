package fer.kotlin.weatherapp.domain.commands

import fer.kotlin.weatherapp.domain.datasource.PastForecastProvider
import fer.kotlin.weatherapp.domain.model.PastForecastList

/**
 * Created by Default on 15/08/2017.
 */
class PastRequestForecastCommand(val station: String, val dateFrom: String, val dateTo: String,
                                 val pastForecastProvider: PastForecastProvider = PastForecastProvider()) : Command<PastForecastList> {

    override fun execute() = pastForecastProvider.requestByDateStation(station, dateFrom, dateTo)
}