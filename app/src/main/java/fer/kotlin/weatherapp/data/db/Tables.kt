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
    val LATITUD = "latitud"
    val PROVINCIA = "provincia"
    val ALTITUD = "altitud"
    val INDICATIVO = "indicativo"
    val NOMBRE = "nombre"
    val INDSINOP = "indsinop"
    val LONGITUD = "longitud"
}