package fer.kotlin.weatherapp.data.db

import fer.kotlin.weatherapp.domain.datasource.ForecastDataSource
import fer.kotlin.weatherapp.domain.datasource.PastForecastDataSource
import fer.kotlin.weatherapp.domain.model.*
import fer.kotlin.weatherapp.extensions.*
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * Created by Default on 10/08/2017.
 */
class ForecastDb(
        val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
        val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource, PastForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " +
                "AND ${DayForecastTable.DATE} >= ?"

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use {
        val forecast = select(DayForecastTable.NAME).byId(id).
            parseOpt { DayForecast(HashMap(it)) }

        if (forecast != null) dataMapper.convertDayToDomain(forecast) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }

    /* Stations */
    fun saveStations(list: StationList) = forecastDbHelper.use {

        clear(StationTable.NAME)

        val listStationDBO = dataMapper.convertStationListFromDomain(list)

        listStationDBO.forEach {
            insert(StationTable.NAME, *it.map.toVarargArray())
        }

    }

    override fun requestStations() = forecastDbHelper.use {

        val stationsList = select(StationTable.NAME)
                .parseList { StationDBO(HashMap(it)) }

        StationList(dataMapper.convertStationListToDomain(stationsList))
    }

    override fun requestForecastByDateStation(station: String, dateFrom: String, dateTo: String): PastForecastList? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

