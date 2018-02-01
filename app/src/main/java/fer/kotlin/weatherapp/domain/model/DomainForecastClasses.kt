package fer.kotlin.weatherapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class ForecastList(val id: Long, val city: String, val country: String, val dailyForecast:List<Forecast>) {
    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)


data class PastForecastList(private val pastForecastList:List<PastForecast>) {
    val size: Int
        get() = pastForecastList.size

    operator fun get(position: Int) = pastForecastList[position]
}

data class PastForecast(val id: Long,
                        val city: String,
                        val fecha: String,
                        val indicativo: String,
                        val nombre: String,
                        val provincia: String,
                        val altitud: String,
                        val tmed: String,
                        val prec: String,
                        val tmin: String,
                        val horatmin: String,
                        val tmax: String,
                        val horatmax: String,
                        val dir: String,
                        val velmedia: String,
                        val racha: String,
                        val horaracha: String,
                        val sol: String,
                        val presMax: String,
                        val horaPresMax: String,
                        val presMin: String,
                        val horaPresMin: String,
                        val iconUrl: String)

data class StationList(val stationsList: List<Station>) {
    val size: Int
        get() = stationsList.size

    operator fun get(position: Int) = stationsList[position]
}

data class Station(val id: Long,
                   val latitud: String,
                   val provincia: String,
                   val altitud: String,
                   val indicativo: String,
                   val nombre: String,
                   val indsinop: String,
                   val longitud: String,
                   var toString: String = nombre)
{
    override fun toString(): String = this.toString
}

data class Province(val idStation: Long,
                    val provincia: String)
{
    override fun toString(): String = this.provincia
}

enum class ListType {
    Provincia, Station
}

data class SelectorStationProvincia(val provincia: String)

data class Herria(var herri_kodea: String, var izena: String, var probintzia: String, var autonomi_erkidegoa: String,
                  var autonomi_erkidegoa_eus: String, var longitudea: String, var latitudea: String) {

    override fun toString(): String = this.izena
}

data class DsForecast(val id: Long, val latitude: String, val longitude: String, val timezone: String,
                      val currently: DsForecastCurrently? = null, val hourly: List<DsForecastHourly>,
                      val daily: List<DsForecastDaily>, val offset: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable<DsForecastCurrently>(DsForecastCurrently::class.java.classLoader),
            parcel.createTypedArrayList(DsForecastHourly),
            parcel.createTypedArrayList(DsForecastDaily),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(timezone)
        parcel.writeTypedList(hourly)
        parcel.writeTypedList(daily)
        parcel.writeString(offset)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DsForecast> {
        override fun createFromParcel(parcel: Parcel): DsForecast = DsForecast(parcel)

        override fun newArray(size: Int): Array<DsForecast?> = arrayOfNulls(size)
    }
}

data class DsForecastCurrently(val id: Long, val idDsForecast: Long, val time: String?, val summary: String?,
                               val icon: String?, val nearestStormDistance: String?, val nearestStormBearing: String?,
                               val precipIntensity: String?, val precipProbability: String?, var temperature: String?,
                               val apparentTemperature: String?, val dewPoint: String?, val humidity: String?,
                               val pressure: String?, val windSpeed: String?, val windGust: String?,
                               val windBearing: String?, val cloudCover: String?, val uvIndex: String?,
                               val visibility: String?, val ozone: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(idDsForecast)
        parcel.writeString(time)
        parcel.writeString(summary)
        parcel.writeString(icon)
        parcel.writeString(nearestStormDistance)
        parcel.writeString(nearestStormBearing)
        parcel.writeString(precipIntensity)
        parcel.writeString(precipProbability)
        parcel.writeString(temperature)
        parcel.writeString(apparentTemperature)
        parcel.writeString(dewPoint)
        parcel.writeString(humidity)
        parcel.writeString(pressure)
        parcel.writeString(windSpeed)
        parcel.writeString(windBearing)
        parcel.writeString(cloudCover)
        parcel.writeString(uvIndex)
        parcel.writeString(visibility)
        parcel.writeString(ozone)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DsForecastHourly> {
        override fun createFromParcel(parcel: Parcel): DsForecastHourly = DsForecastHourly(parcel)

        override fun newArray(size: Int): Array<DsForecastHourly?> = arrayOfNulls(size)
    }
}

data class DsForecastDaily(val id: Long, val idDsForecast: Long, val time: String, val summary: String, val icon: String,
                           val sunriseTime: String, val sunsetTime: String, val moonPhase: String,
                           val precipIntensity: String, val precipIntensityMax: String,
                           val precipIntensityMaxTime: String, val precipProbability: String, val precipType: String,
                           val temperatureHigh: String, val temperatureHighTime: String, val temperatureLow: String,
                           val temperatureLowTime: String, val apparentTemperatureHigh: String,
                           val apparentTemperatureHighTime: String, val apparentTemperatureLow: String,
                           val apparentTemperatureLowTime: String, val dewPoint: String, val humidity: String,
                           val pressure: String, val windSpeed: String, val windBearing: String, val cloudCover: String,
                           val uvIndex: String, val uvIndexTime: String, val visibility: String, val ozone: String,
                           val temperatureMin: String, val temperatureMinTime: String, val temperatureMax: String,
                           val temperatureMaxTime: String, val apparentTemperatureMax: String,
                           val apparentTemperatureMaxTime: String, val apparentTemperatureMin: String,
                           val apparentTemperatureMinTime: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(idDsForecast)
        parcel.writeString(time)
        parcel.writeString(summary)
        parcel.writeString(icon)
        parcel.writeString(sunriseTime)
        parcel.writeString(sunsetTime)
        parcel.writeString(moonPhase)
        parcel.writeString(precipIntensity)
        parcel.writeString(precipIntensityMax)
        parcel.writeString(precipIntensityMaxTime)
        parcel.writeString(precipProbability)
        parcel.writeString(precipType)
        parcel.writeString(temperatureHigh)
        parcel.writeString(temperatureHighTime)
        parcel.writeString(temperatureLow)
        parcel.writeString(temperatureLowTime)
        parcel.writeString(apparentTemperatureHigh)
        parcel.writeString(apparentTemperatureHighTime)
        parcel.writeString(apparentTemperatureLow)
        parcel.writeString(apparentTemperatureLowTime)
        parcel.writeString(dewPoint)
        parcel.writeString(humidity)
        parcel.writeString(pressure)
        parcel.writeString(windSpeed)
        parcel.writeString(windBearing)
        parcel.writeString(cloudCover)
        parcel.writeString(uvIndex)
        parcel.writeString(uvIndexTime)
        parcel.writeString(visibility)
        parcel.writeString(ozone)
        parcel.writeString(temperatureMin)
        parcel.writeString(temperatureMinTime)
        parcel.writeString(temperatureMax)
        parcel.writeString(temperatureMaxTime)
        parcel.writeString(apparentTemperatureMax)
        parcel.writeString(apparentTemperatureMaxTime)
        parcel.writeString(apparentTemperatureMin)
        parcel.writeString(apparentTemperatureMinTime)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DsForecastDaily> {
        override fun createFromParcel(parcel: Parcel): DsForecastDaily = DsForecastDaily(parcel)

        override fun newArray(size: Int): Array<DsForecastDaily?> = arrayOfNulls(size)
    }
}

data class DsForecastHourly(val id: Long, val idDsForecast: Long, val time: String, val summary: String, val icon: String,
                          val precipIntensity: String, val precipProbability: String, val precipType: String,
                          val temperature: String, val apparentTemperature: String, val dewPoint: String,
                          val humidity: String, val pressure: String, val windSpeed: String, val windBearing: String,
                          val cloudCover: String, val uvIndex: String, val visibility: String, val ozone: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeLong(idDsForecast)
        parcel.writeString(time)
        parcel.writeString(summary)
        parcel.writeString(icon)
        parcel.writeString(precipIntensity)
        parcel.writeString(precipProbability)
        parcel.writeString(precipType)
        parcel.writeString(temperature)
        parcel.writeString(apparentTemperature)
        parcel.writeString(dewPoint)
        parcel.writeString(humidity)
        parcel.writeString(pressure)
        parcel.writeString(windSpeed)
        parcel.writeString(windBearing)
        parcel.writeString(cloudCover)
        parcel.writeString(uvIndex)
        parcel.writeString(visibility) 
        parcel.writeString(ozone)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DsForecastHourly> {
        override fun createFromParcel(parcel: Parcel): DsForecastHourly = DsForecastHourly(parcel)

        override fun newArray(size: Int): Array<DsForecastHourly?> = arrayOfNulls(size)
    }
}