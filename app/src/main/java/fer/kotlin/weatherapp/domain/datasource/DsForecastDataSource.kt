package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.Herria
import fer.kotlin.weatherapp.domain.model.HerriaKFilter

/**
 * Created by Default on 03/10/2017.
 */
interface DsForecastDataSource {

    fun requestPastForecast(latitude: String, longitude: String, time: IntArray): DsForecast?

}