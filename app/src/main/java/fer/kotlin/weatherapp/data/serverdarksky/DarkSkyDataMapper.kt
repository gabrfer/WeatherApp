package fer.kotlin.weatherapp.data.serverdarksky

import fer.kotlin.weatherapp.domain.model.DsForecast as DsModelForecast
import fer.kotlin.weatherapp.domain.model.DsForecastCurrently as DsModelForecastCurrently
import fer.kotlin.weatherapp.domain.model.DsForecastHourly as DsModelForecastHourly
import fer.kotlin.weatherapp.domain.model.DsForecastDaily as DsModelForecastDaily

class DarkSkyDataMapper{

    fun convertToDomain(dsForecast: DsForecastResult) = with(dsForecast) {
        DsModelForecast(-1, latitude,  longitude,  timezone, convertCurrentlyToDomain(currently),
                convertHourlyListToDomain(hourly.data), convertDailyListToDomain(daily.data), offset)
    }

    private fun convertCurrentlyToDomain(currently: DsForecastCurrently) = with(currently) {
            DsModelForecastCurrently(-1, -1, time?: "", summary?: "", icon?: "",
                    nearestStormDistance?: "", nearestStormBearing?: "",
                    precipIntensity?: "", precipProbability?: "", temperature?: "",
                    apparentTemperature?: "",  dewPoint?: "",  humidity?: "",  pressure?: "",
                    windSpeed?: "",  windGust?: "", windBearing?: "",  cloudCover?: "",  uvIndex?: "",
                    visibility?: "", ozone?: "") }

    private fun convertHourlyListToDomain(listDsHourly: List<DsForecastHourly>): List<DsModelForecastHourly> =
            listDsHourly.mapIndexed { _, dsForecastHourly -> convertHourlyToDomain(dsForecastHourly) }

    private fun convertHourlyToDomain(dsHourly: DsForecastHourly) = with(dsHourly) {
        DsModelForecastHourly(-1, -1, time?: "", summary?: "", icon?: "", precipIntensity?: "",
                precipProbability?: "", precipType?: "", temperature?: "",
                apparentTemperature?: "",  dewPoint?: "",  humidity?: "",  pressure?: "",
                windSpeed?: "",  windBearing?: "",  cloudCover?: "",  uvIndex?: "",
                visibility?: "", ozone?: "")
    }

    private fun convertDailyListToDomain(listDsDaily: List<DsForecastDaily>): List<DsModelForecastDaily> =
            listDsDaily.mapIndexed { _, dsForecastDaily -> convertDailyToDomain(dsForecastDaily) }

    private fun convertDailyToDomain(dsDaily: DsForecastDaily) = with(dsDaily) {
        DsModelForecastDaily(-1, -1, time, summary, icon,
                sunriseTime?: "", sunsetTime?: "", moonPhase?: "",
                precipIntensity?: "", precipIntensityMax?: "",
                precipIntensityMaxTime?: "", precipProbability?: "",
                precipType?: "", temperatureHigh?: "", temperatureHighTime?: "",
                temperatureLow?: "", temperatureLowTime?: "",
                apparentTemperatureHigh?: "", apparentTemperatureHighTime?: "",
                apparentTemperatureLow?: "", apparentTemperatureLowTime?: "",
                dewPoint?: "", humidity?: "", pressure?: "", windSpeed?: "",
                windBearing?: "", cloudCover?: "", uvIndex?: "", uvIndexTime?: "",
                visibility?: "", ozone?: "", temperatureMin?: "",
                temperatureMinTime?: "", temperatureMax?: "",
                temperatureMaxTime?: "", apparentTemperatureMax?: "",
                apparentTemperatureMaxTime?: "", apparentTemperatureMin?: "",
                apparentTemperatureMinTime?: "")
    }
}