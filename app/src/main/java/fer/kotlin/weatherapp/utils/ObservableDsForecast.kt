package fer.kotlin.weatherapp.utils

import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.DsForecastCurrently
import fer.kotlin.weatherapp.domain.model.DsForecastDaily
import fer.kotlin.weatherapp.domain.model.DsForecastHourly
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class ObservableDsForecast: Observable(), Serializable {

    private lateinit var dsForecast: DsForecast

    fun getDsForecast(): DsForecast? = dsForecast

    fun setDsForecast(forecast: DsForecast) {
        dsForecast = forecast
        this.setChanged()
        this.notifyObservers(forecast)
    }
}

class ObservableDsForecastCurrently: Observable(), Serializable {

    private lateinit var dsForecastCurrently: DsForecastCurrently

    fun getDsForecastCurrently(): DsForecastCurrently = dsForecastCurrently

    fun setDsForecastCurrently(forecastCurrently: DsForecastCurrently) {
        dsForecastCurrently = forecastCurrently
        this.setChanged()
        this.notifyObservers(forecastCurrently)
    }
}

class ObservableDsForecastHourly: Observable(), Serializable {

    private lateinit var dsForecastHourly: ArrayList<DsForecastHourly>

    fun getDsForecasHourly(): ArrayList<DsForecastHourly> = dsForecastHourly

    fun setDsForecastHourly(forecastHourly: ArrayList<DsForecastHourly>) {
        dsForecastHourly = forecastHourly
        this.setChanged()
        this.notifyObservers(forecastHourly)
    }
}

class ObservableDsForecastDaily: Observable(), Serializable {

    private lateinit var dsForecastDaily: ArrayList<DsForecastDaily>

    fun getDsForecastDaily(): ArrayList<DsForecastDaily> = dsForecastDaily

    fun setDsForecastDaily(forecastDaily: ArrayList<DsForecastDaily>) {
        dsForecastDaily = forecastDaily
        this.setChanged()
        this.notifyObservers(forecastDaily)
    }
}