package fer.kotlin.weatherapp.data.serverinthepast

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.fasterxml.jackson.module.kotlin.readValue


/**
 * Created by Default on 13/08/2017.
 */
class PastForecastByDateStation(val station: String, val dateFrom: String, val dateTo: String, val gson: Gson = Gson()) {

    companion object {
        private val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaG9wZXJkaW5hbmRAZ21haWwuY29tIiwianRpIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwiZXhwIjoxNTEwMzkzMzAwLCJpc3MiOiJBRU1FVCIsImlhdCI6MTUwMjYxNzMwMCwidXNlcklkIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwicm9sZSI6IiJ9.0Y9dUdWDDvnWW07gVHjKtlMKfdaqvY-6IQ-kLOgBGjE"
        private val URL = "https://opendata.aemet.es/opendata/api/valores/climatologicos/diarios/datos/fechaini/_dateFrom/fechafin/_dateTo/estacion/_station/?api_key="
        private val COMPLETE_URL = URL+API_KEY
    }

    fun execute(): PastForecastResult {

        val finalURL : String = COMPLETE_URL.replace("_dateFrom", dateFrom).replace("_dateTo", dateTo).replace("_station", station)
        val firstCallJsonStr = java.net.URL(finalURL).readText()
        val pastForecastFirstCallResult : PastForecastFirstCallResult = gson.fromJson(firstCallJsonStr, PastForecastFirstCallResult::class.java)

        val pastForecastJsonStr = java.net.URL(pastForecastFirstCallResult.datos).readText()

        val mapper = jacksonObjectMapper()
        val pastForecastList = mapper.readValue<List<PastForecast>>(pastForecastJsonStr)

        return PastForecastResult(pastForecastList)
    }
}