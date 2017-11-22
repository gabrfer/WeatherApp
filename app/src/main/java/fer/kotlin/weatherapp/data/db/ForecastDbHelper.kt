package fer.kotlin.weatherapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import fer.kotlin.weatherapp.ui.App
import org.jetbrains.anko.db.*

class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 16
        val instance by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.createTable(CityForecastTable.NAME, true,
                CityForecastTable.ID to INTEGER + PRIMARY_KEY,
                CityForecastTable.CITY to TEXT,
                CityForecastTable.COUNTRY to TEXT)

        db.createTable(DayForecastTable.NAME, true,
                DayForecastTable.ID to SqlType.create("INTEGER PRIMARY KEY AUTOINCREMENT"),
                DayForecastTable.DATE to INTEGER,
                DayForecastTable.DESCRIPTION to TEXT,
                DayForecastTable.HIGH to INTEGER,
                DayForecastTable.LOW to INTEGER,
                DayForecastTable.ICON_URL to TEXT,
                DayForecastTable.CITY_ID to INTEGER)

        db.createTable(StationTable.NAME, true,
                StationTable.ID to SqlType.create("INTEGER PRIMARY KEY AUTOINCREMENT"),
                StationTable.LATITUD to TEXT,
                StationTable.PROVINCIA to TEXT,
                StationTable.ALTITUD to TEXT,
                StationTable.INDICATIVO to TEXT,
                StationTable.NOMBRE to TEXT,
                StationTable.INDSINOP to TEXT,
                StationTable.LONGITUD to TEXT)

        db.createTable(DsForecastTable.NAME, true,
                DsForecastTable.ID to SqlType.create("INTEGER PRIMARY KEY AUTOINCREMENT"),
                DsForecastTable.LATITUDE to TEXT,
                DsForecastTable.LONGITUDE to TEXT,
                DsForecastTable.TIMEZONE to TEXT,
                DsForecastTable.TIME to TEXT,
                DsForecastTable.SUMMARY to TEXT,
                DsForecastTable.ICON to TEXT,
                DsForecastTable.SUNRISETIME to TEXT,
                DsForecastTable.SUNSETTIME to TEXT,
                DsForecastTable.MOONPHASE to TEXT,
                DsForecastTable.PRECIPINTENSITY to TEXT,
                DsForecastTable.PRECIPINTENSITYMAX to TEXT,
                DsForecastTable.PRECIPINTENSITYMAXTIME to TEXT,
                DsForecastTable.PRECIPPROBABILITY to TEXT,
                DsForecastTable.PRECIPTYPE to TEXT,
                DsForecastTable.TEMPERATUREHIGH to TEXT,
                DsForecastTable.TEMPERATUREHIGHTIME to TEXT,
                DsForecastTable.TEMPERATURELOW to TEXT,
                DsForecastTable.TEMPERATURELOWTIME to TEXT,
                DsForecastTable.APPARENTTEMPERATUREHIGH to TEXT,
                DsForecastTable.APPARENTTEMPERATUREHIGHTIME to TEXT,
                DsForecastTable.APPARENTTEMPERATURELOW to TEXT,
                DsForecastTable.APPARENTTEMPERATURELOWTIME to TEXT,
                DsForecastTable.DEWPOINT to TEXT,
                DsForecastTable.HUMIDITY to TEXT,
                DsForecastTable.PRESSURE to TEXT,
                DsForecastTable.WINDSPEED to TEXT,
                DsForecastTable.WINDBEARING to TEXT,
                DsForecastTable.CLOUDCOVER to TEXT,
                DsForecastTable.UVINDEX to TEXT,
                DsForecastTable.UVINDEXTIME to TEXT,
                DsForecastTable.VISIBILITY to TEXT,
                DsForecastTable.TEMPERATUREMIN to TEXT,
                DsForecastTable.TEMPERATUREMINTIME to TEXT,
                DsForecastTable.TEMPERATUREMAX to TEXT,
                DsForecastTable.TEMPERATUREMAXTIME to TEXT,
                DsForecastTable.APPARENTTEMPERATUREMAX to TEXT,
                DsForecastTable.APPARENTTEMPERATUREMAXTIME to TEXT,
                DsForecastTable.APPARENTTEMPERATUREMIN to TEXT,
                DsForecastTable.APPARENTTEMPERATUREMINTIME to TEXT,
                DsForecastTable.OFFSET to TEXT)

        db.createTable(DsForecastUnitTable.NAME, true,
                DsForecastUnitTable.ID to SqlType.create("INTEGER PRIMARY KEY AUTOINCREMENT"),
                DsForecastUnitTable.ID_DSFORECAST to INTEGER,
                DsForecastUnitTable.TIME to TEXT,
                DsForecastUnitTable.SUMMARY to TEXT,
                DsForecastUnitTable.ICON to TEXT,
                DsForecastUnitTable.PRECIPINTENSITY to TEXT,
                DsForecastUnitTable.PRECIPPROBABILITY to TEXT,
                DsForecastUnitTable.PRECIPTYPE to TEXT,
                DsForecastUnitTable.TEMPERATURE to TEXT,
                DsForecastUnitTable.APPARENTTEMPERATURE to TEXT,
                DsForecastUnitTable.DEWPOINT to TEXT,
                DsForecastUnitTable.HUMIDITY to TEXT,
                DsForecastUnitTable.PRESSURE to TEXT,
                DsForecastUnitTable.WINDSPEED to TEXT,
                DsForecastUnitTable.WINDBEARING to TEXT,
                DsForecastUnitTable.CLOUDCOVER to TEXT,
                DsForecastUnitTable.UVINDEX to TEXT,
                DsForecastUnitTable.VISIBILITy to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        db.dropTable(StationTable.NAME, true)
        db.dropTable(DsForecastUnitTable.NAME, true)
        db.dropTable(DsForecastTable.NAME, true)
        onCreate(db)
    }

}