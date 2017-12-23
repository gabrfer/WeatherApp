package fer.kotlin.weatherapp.data.db

/**
 * Created by Default on 10/08/2017.
 */
class CityForecast(val map: MutableMap<String, Any?>, val dailyForecast: List<DayForecast>) {

     var _id: Long by map
     var city: String by map
     var country: String by map

     constructor(id: Long, city: String, country: String,
                 dailyForecast: List<DayForecast>) : this(HashMap(), dailyForecast) {
            this._id = id
            this.city = city
            this.country = country
     }
}

class DayForecast(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityId: Long by map

    constructor(date: Long, description: String, high: Int, low: Int,
                iconUrl: String, cityId: Long) : this(HashMap())
    {
        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityId = cityId
    }
}

class StationDBO(var map: MutableMap<String, Any?>) {
    var _id: Long by map
    var latitud: String by map
    var provincia: String by map
    var altitud: String by map
    var indicativo: String by map
    var nombre: String by map
    var indsinop: String by map
    var longitud: String by map

    constructor(latitud: String, provincia: String, altitud: String, indicativo: String,
                nombre: String, indsinop: String, longitud: String) : this(HashMap())
    {
        this.latitud = latitud
        this.provincia = provincia
        this.altitud = altitud
        this.indicativo = indicativo
        this.nombre = nombre
        this.indsinop = indsinop
        this.longitud = longitud
    }
}

class DsForecastHourly(val map: MutableMap<String, Any?>) {

    var _id: Long by map
    var idDsForecast: Long by map
    var time: String by map 
    var summary: String by map 
    var icon: String by map
    var precipIntensity: String by map
    var precipProbability: String by map
    var precipType: String by map 
    var temperature: String by map
    var apparentTemperature: String by map 
    var dewPoint: String by map 
    var humidity: String by map 
    var pressure: String by map
    var windSpeed: String by map 
    var windBearing: String by map 
    var cloudCover: String by map 
    var uvIndex: String by map
    var visibility: String by map
    var ozone: String by map
    
    constructor(idDsForecast: Long, time: String, summary: String, icon: String, precipIntensity: String,
                precipProbability: String, precipType: String, temperature: String, apparentTemperature: String,
                dewPoint: String, humidity: String, pressure: String, windSpeed: String, windBearing: String,
                cloudCover: String, uvIndex: String, visibility: String, ozone: String): this(HashMap())
    {
        this.idDsForecast = idDsForecast
        this.time = time
        this.summary = summary
        this.icon = icon
        this.precipIntensity = precipIntensity
        this.precipProbability = precipProbability
        this.precipType = precipType
        this.temperature = temperature
        this.apparentTemperature = apparentTemperature
        this.dewPoint = dewPoint
        this.humidity = humidity
        this.pressure = pressure
        this.windSpeed = windSpeed
        this.windBearing = windBearing
        this.cloudCover = cloudCover
        this.uvIndex = uvIndex
        this.visibility = visibility
        this.ozone = ozone
    }
    
}

class DsForecast(val map: MutableMap<String, Any?>, var hourly: List<DsForecastHourly>) {

    var _id: Long by map
    var latitude: String by map
    var longitude: String by map
    var timezone: String by map

    var time: String by map
    var summary: String by map
    var icon: String by map
    var sunriseTime: String by map
    var sunsetTime: String by map
    var moonPhase: String by map
    var precipIntensity: String by map
    var precipIntensityMax: String by map
    var precipIntensityMaxTime: String by map
    var precipProbability: String by map
    var precipType: String by map
    var temperatureHigh: String by map
    var temperatureHighTime: String by map
    var temperatureLow: String by map
    var temperatureLowTime: String by map
    var apparentTemperatureHigh: String by map
    var apparentTemperatureHighTime: String by map
    var apparentTemperatureLow: String by map
    var apparentTemperatureLowTime: String by map
    var dewPoint: String by map
    var humidity: String by map
    var pressure: String by map
    var windSpeed: String by map
    var windBearing: String by map
    var cloudCover: String by map
    var uvIndex: String by map
    var uvIndexTime: String by map
    var visibility: String by map
    var ozone: String by map
    var temperatureMin: String by map
    var temperatureMinTime: String by map
    var temperatureMax: String by map
    var temperatureMaxTime: String by map
    var apparentTemperatureMax: String by map
    var apparentTemperatureMaxTime: String by map
    var apparentTemperatureMin: String by map
    var apparentTemperatureMinTime: String by map

    var offset: String by map

    constructor(latitude: String, longitude: String, timezone: String, hourly: List<DsForecastHourly>, time: String,
                summary: String, icon: String, sunriseTime: String, sunsetTime: String, moonPhase: String,
                precipIntensity: String, precipIntensityMax: String, precipIntensityMaxTime: String, precipProbability: String,
                precipType: String, temperatureHigh: String, temperatureHighTime: String, temperatureLow: String,
                temperatureLowTime: String, apparentTemperatureHigh: String, apparentTemperatureHighTime: String,
                apparentTemperatureLow: String, apparentTemperatureLowTime: String, dewPoint: String, humidity: String,
                pressure: String, windSpeed: String, windBearing: String, cloudCover: String, uvIndex: String, uvIndexTime: String,
                visibility: String, ozone: String, temperatureMin: String, temperatureMinTime: String, temperatureMax: String,
                temperatureMaxTime: String, apparentTemperatureMax: String, apparentTemperatureMaxTime: String,
                apparentTemperatureMin: String, apparentTemperatureMinTime: String, offset: String): this(HashMap(), hourly)
    {
        this.latitude = latitude
        this.longitude = longitude
        this.timezone = timezone

        this.time = time
        this.summary = summary
        this.icon = icon
        this.sunriseTime = sunriseTime
        this.sunsetTime = sunsetTime
        this.moonPhase = moonPhase
        this.precipIntensity = precipIntensity
        this.precipIntensityMax = precipIntensityMax
        this.precipIntensityMaxTime = precipIntensityMaxTime
        this.precipProbability = precipProbability
        this.precipType = precipType
        this.temperatureHigh = temperatureHigh
        this.temperatureHighTime = temperatureHighTime
        this.temperatureLow = temperatureLow
        this.temperatureLowTime = temperatureLowTime
        this.apparentTemperatureHigh = apparentTemperatureHigh
        this.apparentTemperatureHighTime = apparentTemperatureHighTime
        this.apparentTemperatureLow = apparentTemperatureLow
        this.apparentTemperatureLowTime = apparentTemperatureLowTime
        this.dewPoint = dewPoint
        this.humidity = humidity
        this.pressure = pressure
        this.windSpeed = windSpeed
        this.windBearing = windBearing
        this.cloudCover = cloudCover
        this.uvIndex = uvIndex
        this.uvIndexTime = uvIndexTime
        this.visibility = visibility
        this.ozone = ozone
        this.temperatureMin = temperatureMin
        this.temperatureMinTime = temperatureMinTime
        this.temperatureMax = temperatureMax
        this.temperatureMaxTime = temperatureMaxTime
        this.apparentTemperatureMax = apparentTemperatureMax
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime
        this.apparentTemperatureMin = apparentTemperatureMin
        this.apparentTemperatureMinTime = apparentTemperatureMinTime

        this.offset = offset
    }
}

