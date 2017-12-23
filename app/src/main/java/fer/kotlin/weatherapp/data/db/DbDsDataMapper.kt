package fer.kotlin.weatherapp.data.db

import fer.kotlin.weatherapp.domain.model.DsForecast as DsModelForecast
import fer.kotlin.weatherapp.domain.model.DsForecastCurrently as DsModelForecastCurrently
import fer.kotlin.weatherapp.domain.model.DsForecastHourly as DsModelForecastHourly
import fer.kotlin.weatherapp.domain.model.DsForecastDaily as DsModelForecastDaily


class DsDataMapper{

    fun convertFromDomain(modelForecast: DsModelForecast) = with(modelForecast) {
        val hourly = convertHourlyListFromDomain(hourly)
        DsForecast(latitude, longitude, timezone, hourly, daily[0].time, daily[0].summary, daily[0].icon,
                daily[0].sunriseTime, daily[0].sunsetTime,
                daily[0].moonPhase, daily[0].precipIntensity, daily[0].precipIntensityMax, daily[0].precipIntensityMaxTime,
                daily[0].precipProbability, daily[0].precipType, daily[0].temperatureHigh, daily[0].temperatureHighTime,
                daily[0].temperatureLow, daily[0].temperatureLowTime, daily[0].apparentTemperatureHigh,
                daily[0].apparentTemperatureHighTime, daily[0].apparentTemperatureLow, daily[0].apparentTemperatureLowTime,
                daily[0].dewPoint, daily[0].humidity, daily[0].pressure, daily[0].windSpeed, daily[0].windBearing,
                daily[0].cloudCover, daily[0].uvIndex, daily[0].uvIndexTime, daily[0].visibility, daily[0].ozone,
                daily[0].temperatureMin, daily[0].temperatureMinTime, daily[0].temperatureMax,
                daily[0].temperatureMaxTime, daily[0].apparentTemperatureMax, daily[0].apparentTemperatureMaxTime,
                daily[0].apparentTemperatureMin, daily[0].apparentTemperatureMinTime, offset)
    }

    private fun convertHourlyListFromDomain(modelListDsHourly: List<DsModelForecastHourly>): List<DsForecastHourly> =
            modelListDsHourly.mapIndexed { _, dsForecastUnit -> convertHourlyFromDomain(dsForecastUnit)  }

    private fun convertHourlyFromDomain(modelDsHourly: DsModelForecastHourly) = with(modelDsHourly){
        DsForecastHourly(idDsForecast, time, summary, icon, precipIntensity,
                precipProbability, precipType, temperature,
                apparentTemperature, dewPoint, humidity, pressure,
                windSpeed, windBearing, cloudCover, uvIndex,
                visibility, ozone)
    }

    fun convertToDomain(dsForecast: DsForecast) = with(dsForecast) {
        DsModelForecast(_id, latitude, longitude, timezone, null, convertHourlyListToDomain(hourly),
                List(1) { DsModelForecastDaily(-1, _id, time, summary, icon, sunriseTime, sunsetTime,
                        moonPhase, precipIntensity, precipIntensityMax, precipIntensityMaxTime,
                        precipProbability, precipType, temperatureHigh, temperatureHighTime,
                        temperatureLow, temperatureLowTime, apparentTemperatureHigh,
                        apparentTemperatureHighTime, apparentTemperatureLow, apparentTemperatureLowTime,
                        dewPoint, humidity, pressure, windSpeed, windBearing,
                        cloudCover, uvIndex, uvIndexTime, visibility, ozone,
                        temperatureMin, temperatureMinTime, temperatureMax,
                        temperatureMaxTime, apparentTemperatureMax, apparentTemperatureMaxTime,
                        apparentTemperatureMin, apparentTemperatureMinTime)}
                , offset)
    }

    private fun convertHourlyListToDomain(listDsHourly: List<DsForecastHourly>): List<DsModelForecastHourly> =
            listDsHourly.mapIndexed { _, dsForecastHourly -> convertHourlyToDomain(dsForecastHourly) }

    private fun convertHourlyToDomain(dsHourly: DsForecastHourly) = with(dsHourly) {
        DsModelForecastHourly(_id, idDsForecast, time, summary, icon, precipIntensity,
                precipProbability, precipType, temperature,
                apparentTemperature, dewPoint, humidity, pressure,
                windSpeed, windBearing, cloudCover, uvIndex,
                visibility, ozone)
    }
}