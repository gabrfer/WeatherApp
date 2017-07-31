package fer.kotlin.weatherapp.domain.commands

import fer.kotlin.weatherapp.data.ForecastRequest
import fer.kotlin.weatherapp.domain.mappers.ForecastDataMapper
import fer.kotlin.weatherapp.domain.model.ForecastList

/**
 * Created by Default on 30/07/2017.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}