package fer.kotlin.weatherapp.data.db

import fer.kotlin.weatherapp.domain.datasource.DsForecastDataSource
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.extensions.ToDateUTC
import fer.kotlin.weatherapp.extensions.parseList
import fer.kotlin.weatherapp.extensions.toVarargArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class DsForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                   private val dataMapper: DsDataMapper = DsDataMapper()) : DsForecastDataSource {

    override fun requestPastForecast(latitude: String, longitude: String, time: IntArray): DsForecast? = forecastDbHelper.use {

        var result: DsForecast? = null

        val dateUTC = time.ToDateUTC(false)

        val dayRequest = "${DsForecastTable.LATITUDE} = ? " +
                "AND ${DsForecastTable.LONGITUDE} = ?" +
                "AND ${DsForecastTable.TIME} = ? "

        val dayForecast = select(DsForecastTable.NAME)
                .whereSimple(dayRequest, latitude, longitude, dateUTC)
                .parseList { DsForecast(HashMap(it), emptyList()) }

        if (dayForecast.isNotEmpty()) {

            val idDayForecast = dayForecast[0]._id

            val hoursRequest = "${DsForecastUnitTable.ID_DSFORECAST} = ? "

            val hoursForecast = select(DsForecastUnitTable.NAME)
                    .whereSimple(hoursRequest, idDayForecast.toString())
                    .parseList { DsForecastHourly(HashMap(it)) }

            dayForecast[0].hourly = hoursForecast

            result = dataMapper.convertToDomain(dayForecast[0])
        }

        return@use result
    }

    fun saveDsForecast(dsForecast: DsForecast) = forecastDbHelper.use {

        with(dataMapper.convertFromDomain(dsForecast)) {
            val idDsForecast:Long = insert(DsForecastTable.NAME, *map.toVarargArray())
            hourly.forEach {
                it.idDsForecast = idDsForecast
                insert(DsForecastUnitTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}