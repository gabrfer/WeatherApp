package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.domain.model.ForecastList

/**
 * Created by Default on 11/08/2017.
 */
interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}