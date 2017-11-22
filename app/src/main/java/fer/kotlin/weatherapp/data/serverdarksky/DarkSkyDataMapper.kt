package fer.kotlin.weatherapp.data.serverdarksky

import fer.kotlin.weatherapp.domain.model.DsForecast as DsModelForecast
import fer.kotlin.weatherapp.domain.model.DsForecastUnit as DsModelForecastUnit

class DarkSkyDataMapper{

    fun convertToDomain(dsForecast: DsForecastResult, dateWithoutTime: String) = with(dsForecast) {
        DsModelForecast(-1, latitude,  longitude,  timezone,  convertHourlyListToDomain(hourly.data)
                ,dateWithoutTime,daily.data[0].summary,daily.data[0].icon,daily.data[0].sunriseTime?: "",daily.data[0].sunsetTime?: ""
                ,daily.data[0].moonPhase?: "",daily.data[0].precipIntensity?: "", daily.data[0].precipIntensityMax?: ""
                ,daily.data[0].precipIntensityMaxTime?: "", daily.data[0].precipProbability?: "", daily.data[0].precipType?: ""
                ,daily.data[0].temperatureHigh?: "",daily.data[0].temperatureHighTime?: ""
                ,daily.data[0].temperatureLow?: "",daily.data[0].temperatureLowTime?: "",daily.data[0].apparentTemperatureHigh?: ""
                ,daily.data[0].apparentTemperatureHighTime?: "",daily.data[0].apparentTemperatureLow?: "",daily.data[0].apparentTemperatureLowTime?: ""
                ,daily.data[0].dewPoint?: "",daily.data[0].humidity?: "",daily.data[0].pressure?: "",daily.data[0].windSpeed?: "",daily.data[0].windBearing?: ""
                ,daily.data[0].cloudCover?: "",daily.data[0].uvIndex?: "",daily.data[0].uvIndexTime?: "",daily.data[0].visibility?: ""
                ,daily.data[0].temperatureMin?: "",daily.data[0].temperatureMinTime?: "",daily.data[0].temperatureMax?: ""
                ,daily.data[0].temperatureMaxTime?: "",daily.data[0].apparentTemperatureMax?: "",daily.data[0].apparentTemperatureMaxTime?: ""
                ,daily.data[0].apparentTemperatureMin?: "",daily.data[0].apparentTemperatureMinTime?: "", offset)
    }

    private fun convertHourlyListToDomain(listDsHourly: List<DsForecastHourly>): List<DsModelForecastUnit> =
            listDsHourly.mapIndexed { _, dsForecastUnit -> convertHourlyToDomain(dsForecastUnit) }

    private fun convertHourlyToDomain(dsHourly: DsForecastHourly) = with(dsHourly) {
        DsModelForecastUnit(-1, -1, time?: "", summary?: "", icon?: "", precipIntensity?: "",
                precipProbability?: "", precipType?: "", temperature?: "",
                apparentTemperature?: "",  dewPoint?: "",  humidity?: "",  pressure?: "",
                windSpeed?: "",  windBearing?: "",  cloudCover?: "",  uvIndex?: "",
                visibility?: "")
    }
}