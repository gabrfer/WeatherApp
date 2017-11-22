package fer.kotlin.weatherapp.data.db

/**
 * Created by Default on 08/08/2017.
 */
object CityForecastTable {
    val NAME = "CityForecast"
    val ID = "_id"
    val CITY = "city"
    val COUNTRY = "country"
}

object DayForecastTable {
    val NAME = "DayForecast"
    val ID = "_id"
    val DATE = "date"
    val DESCRIPTION = "description"
    val HIGH = "high"
    val LOW = "low"
    val ICON_URL = "iconUrl"
    val CITY_ID = "cityId"
}

object PastForecastTable {
    val NAME = "PastForecast"
    val ID = "_id"
    val FECHA = "fecha"
    val INDICATIVO = "indicativo"
    val NOMBRE = "nombre"
    val PROVINCIA = "provincia"
    val ALTITUD = "altitud"
    val TMED = "tmed"
    val PREC = "prec"
    val TMIN = "tmin"
    val HORATMIN = "hora"
    val TMAX = "tmax"
    val HORATMAX = "horatmax"
    val DIR = "dir"
    val VELMEDIA = "velmedia"
    val RACHA = "racha"
    val HORARACHA = "horaracha"
    val SOL = "sol"
    val PRESMAX = "presmax"
    val HORAPRESMAX = "horapresmax"
    val PRESMIN = "presmin"
    val HORAPRESMIN = "horapresmin"
}

object StationTable {
    val NAME = "Station"
    val ID = "_id"
    val LATITUD = "latitud"
    val PROVINCIA = "provincia"
    val ALTITUD = "altitud"
    val INDICATIVO = "indicativo"
    val NOMBRE = "nombre"
    val INDSINOP = "indsinop"
    val LONGITUD = "longitud"
}

object DsForecastTable {
    
    val NAME = "DsForecast"
    val ID = "_id"

    val LATITUDE = "latitude"
    val LONGITUDE = "longitude"
    val TIMEZONE = "timezone"
    val TIME = "time"
    val SUMMARY = "summary"
    val ICON = "icon"
    val SUNRISETIME = "sunriseTime"
    val SUNSETTIME = "sunsetTime"
    val MOONPHASE = "moonPhase"
    val PRECIPINTENSITY = "precipIntensity"
    val PRECIPINTENSITYMAX = "precipIntensityMax"
    val PRECIPINTENSITYMAXTIME = "precipIntensityMaxTime"
    val PRECIPPROBABILITY = "precipProbability"
    val PRECIPTYPE = "precipType"
    val TEMPERATUREHIGH = "temperatureHigh"
    val TEMPERATUREHIGHTIME = "temperatureHighTime"
    val TEMPERATURELOW = "temperatureLow"
    val TEMPERATURELOWTIME = "temperatureLowTime"
    val APPARENTTEMPERATUREHIGH = "apparentTemperatureHigh"
    val APPARENTTEMPERATUREHIGHTIME = "apparentTemperatureHighTime"
    val APPARENTTEMPERATURELOW = "apparentTemperatureLow"
    val APPARENTTEMPERATURELOWTIME = "apparentTemperatureLowTime"
    val DEWPOINT = "dewPoint"
    val HUMIDITY = "humidity"
    val PRESSURE = "pressure"
    val WINDSPEED = "windSpeed"
    val WINDBEARING = "windBearing"
    val CLOUDCOVER = "cloudCover"
    val UVINDEX = "uvIndex"
    val UVINDEXTIME = "uvIndexTime"
    val VISIBILITY = "visibility"
    val TEMPERATUREMIN = "temperatureMin"
    val TEMPERATUREMINTIME = "temperatureMinTime"
    val TEMPERATUREMAX = "temperatureMax"
    val TEMPERATUREMAXTIME = "temperatureMaxTime"
    val APPARENTTEMPERATUREMAX = "apparentTemperatureMax"
    val APPARENTTEMPERATUREMAXTIME = "apparentTemperatureMaxTime"
    val APPARENTTEMPERATUREMIN = "apparentTemperatureMin"
    val APPARENTTEMPERATUREMINTIME = "apparentTemperatureMinTime"
    val OFFSET = "offset"
}

object DsForecastUnitTable {

    val NAME = "DsForecastUnit"
    val ID = "_id"

    val ID_DSFORECAST = "idDsForecast"
    val TIME = "time"
    val SUMMARY = "summary"
    val ICON = "icon"
    val PRECIPINTENSITY = "precipIntensity"
    val PRECIPPROBABILITY = "precipProbability"
    val PRECIPTYPE = "precipType"
    val TEMPERATURE = "temperature"
    val APPARENTTEMPERATURE = "apparentTemperature"
    val DEWPOINT = "dewPoint"
    val HUMIDITY = "humidity"
    val PRESSURE = "pressure"
    val WINDSPEED = "windSpeed"
    val WINDBEARING = "windBearing"
    val CLOUDCOVER = "cloudCover"
    val UVINDEX = "uvIndex"
    val VISIBILITy = "visibility"
}