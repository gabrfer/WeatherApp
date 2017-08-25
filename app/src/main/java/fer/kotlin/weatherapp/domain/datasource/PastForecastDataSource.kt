package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.domain.model.PastForecast
import fer.kotlin.weatherapp.domain.model.PastForecastList

/**
 * Created by Default on 15/08/2017.
 */
interface PastForecastDataSource {

    fun requestForecastByDateStation(station: String, dateFrom: String, dateTo: String): PastForecastList?

}