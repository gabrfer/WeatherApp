package fer.kotlin.weatherapp.data.serverdarksky


data class DsForecastResult(val latitude: String, val longitude: String, val timezone: String,
                            val currently: DsForecastCurrently, val hourly: DsHourly,
                            val daily: DsDaily, val flags: DsFlags, val offset: String)

data class DsHourly(val summary: String, val icon: String, val data: List<DsForecastHourly>)

data class DsDaily(val data: List<DsForecastDaily>)

data class DsForecastCurrently(val time: String?, val summary: String?, val icon: String?,
                               val nearestStormDistance: String?, val nearestStormBearing: String?,
                               val precipIntensity: String?, val precipProbability: String?, val temperature: String?,
                               val apparentTemperature: String?, val dewPoint: String?, val humidity: String?,
                               val pressure: String?, val windSpeed: String?, val windGust: String?,
                               val windBearing: String?, val cloudCover: String?, val uvIndex: String?,
                               val visibility: String?, val ozone: String?)

data class DsForecastDaily(val time: String, val summary: String, val icon: String, val sunriseTime: String?, val sunsetTime: String?,
                           val moonPhase: String?, val precipIntensity: String?, val precipIntensityMax: String?,
                           val precipIntensityMaxTime: String?, val precipProbability: String?, val precipType: String?,
                           val temperatureHigh: String?, val temperatureHighTime: String?, val temperatureLow: String?,
                           val temperatureLowTime: String?, val apparentTemperatureHigh: String?,
                           val apparentTemperatureHighTime: String?, val apparentTemperatureLow: String?,
                           val apparentTemperatureLowTime: String?, val dewPoint: String?, val humidity: String?,
                           val pressure: String?, val windSpeed: String?, val windBearing: String?, val cloudCover: String?,
                           val uvIndex: String?, val uvIndexTime: String?, val visibility: String?, val ozone: String?,
                           val temperatureMin: String?, val temperatureMinTime: String?, val temperatureMax: String?,
                           val temperatureMaxTime: String?, val apparentTemperatureMax: String?,
                           val apparentTemperatureMaxTime: String?, val apparentTemperatureMin: String?,
                           val apparentTemperatureMinTime: String?)

data class DsForecastHourly(val time: String?, val summary: String?, val icon: String?, val precipIntensity: String?,
                            val precipProbability: String?, val precipType: String?, val temperature: String?,
                            val apparentTemperature: String?, val dewPoint: String?, val humidity: String?,
                            val pressure: String?, val windSpeed: String?, val windGust: String?,
                            val windBearing: String?, val cloudCover: String?, val uvIndex: String?,
                            val visibility: String?, val ozone: String?)

data class DsFlags(val sources: List<String>, val isdStations: List<String>, val units: String)