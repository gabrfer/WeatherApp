package fer.kotlin.weatherapp.data.db

import fer.kotlin.weatherapp.domain.model.DsForecast as DsModelForecast
import fer.kotlin.weatherapp.domain.model.DsForecastUnit as DsModelForecastUnit
/**
 * Created by Default on 03/10/2017.
 */
class DsDataMapper{

    fun convertFromDomain(modelForecast: DsModelForecast) = with(modelForecast) {
        val hourly = convertHourlyListFromDomain(hourly!!)
        DsForecast(latitude, longitude, timezone, hourly, time, summary, icon, sunriseTime, sunsetTime,
                    moonPhase,  precipIntensity, precipIntensityMax, precipIntensityMaxTime,
                    precipProbability, precipType, temperatureHigh, temperatureHighTime,
                    temperatureLow, temperatureLowTime, apparentTemperatureHigh,
                    apparentTemperatureHighTime, apparentTemperatureLow, apparentTemperatureLowTime,
                    dewPoint, humidity, pressure, windSpeed, windBearing,
                    cloudCover, uvIndex, uvIndexTime, visibility,
                    temperatureMin, temperatureMinTime, temperatureMax,
                    temperatureMaxTime, apparentTemperatureMax, apparentTemperatureMaxTime,
                    apparentTemperatureMin, apparentTemperatureMinTime, offset)
    }

    private fun convertHourlyListFromDomain(modelListDsHourly: List<DsModelForecastUnit>): List<DsForecastUnit> {
        return modelListDsHourly.mapIndexed { i, dsForecastUnit -> convertHourlyFromDomain(dsForecastUnit)  }
    }

    private fun convertHourlyFromDomain(modelDsHourly: DsModelForecastUnit) = with(modelDsHourly){
        DsForecastUnit(idDsForecast, time, summary, icon, precipIntensity,
                precipProbability, precipType, temperature,
                apparentTemperature, dewPoint, humidity, pressure,
                windSpeed, windBearing, cloudCover, uvIndex,
                visibility)
    }

    fun convertToDomain(dsForecast: DsForecast) = with(dsForecast) {
        DsModelForecast(_id, latitude, longitude, timezone, convertHourlyListToDomain(hourly!!),
                time, summary, icon, sunriseTime, sunsetTime,
                moonPhase, precipIntensity, precipIntensityMax, precipIntensityMaxTime,
                precipProbability, precipType, temperatureHigh, temperatureHighTime,
                temperatureLow, temperatureLowTime, apparentTemperatureHigh,
                apparentTemperatureHighTime, apparentTemperatureLow, apparentTemperatureLowTime,
                dewPoint, humidity, pressure, windSpeed, windBearing,
                cloudCover, uvIndex, uvIndexTime, visibility,
                temperatureMin, temperatureMinTime, temperatureMax,
                temperatureMaxTime, apparentTemperatureMax, apparentTemperatureMaxTime,
                apparentTemperatureMin, apparentTemperatureMinTime, offset)
    }

    private fun convertHourlyListToDomain(listDsHourly: List<DsForecastUnit>): List<DsModelForecastUnit> {
        return listDsHourly.mapIndexed { i, dsForecastUnit -> convertHourlyToDomain(dsForecastUnit) }
    }

    private fun convertHourlyToDomain(dsHourly: DsForecastUnit) = with(dsHourly) {
        DsModelForecastUnit(_id, idDsForecast, time, summary, icon, precipIntensity,
                precipProbability, precipType, temperature,
                apparentTemperature, dewPoint, humidity, pressure,
                windSpeed, windBearing, cloudCover, uvIndex,
                visibility)
    }
}