package fer.kotlin.weatherapp.data.serverinthepast

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.gson.Gson
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.Reader


/**
* Created by Default on 13/08/2017.
*/
class PastForecastByDateStation(val station: String, val dateFrom: String, val dateTo: String, val okHttpSslClient: OkHttpClient) {

    companion object {
        private val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaG9wZXJkaW5hbmRAZ21haWwuY29tIiwianRpIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwiZXhwIjoxNTEwMzkzMzAwLCJpc3MiOiJBRU1FVCIsImlhdCI6MTUwMjYxNzMwMCwidXNlcklkIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwicm9sZSI6IiJ9.0Y9dUdWDDvnWW07gVHjKtlMKfdaqvY-6IQ-kLOgBGjE"
        private val URL = "https://opendata.aemet.es/opendata/api/valores/climatologicos/diarios/datos/fechaini/_dateFrom/fechafin/_dateTo/estacion/_station/?api_key="
        private val COMPLETE_URL = URL+API_KEY
    }

    fun execute(): PastForecastResult {

        val finalURL : String = COMPLETE_URL.replace("_dateFrom", dateFrom).replace("_dateTo", dateTo).replace("_station", station)

        val responseFinalCallStr = GetAemetCallResult(finalURL, okHttpSslClient)

        val mapper = jacksonObjectMapper()
        val pastForecastList = mapper.readValue<List<PastForecast>>(responseFinalCallStr)

        return PastForecastResult(pastForecastList)
    }
}

class PastForecastStations(val okHttpSslClient: OkHttpClient) {

    companion object {
        private val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaG9wZXJkaW5hbmRAZ21haWwuY29tIiwianRpIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwiZXhwIjoxNTEwMzkzMzAwLCJpc3MiOiJBRU1FVCIsImlhdCI6MTUwMjYxNzMwMCwidXNlcklkIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwicm9sZSI6IiJ9.0Y9dUdWDDvnWW07gVHjKtlMKfdaqvY-6IQ-kLOgBGjE"
        private val URL = "https://opendata.aemet.es/opendata/api/valores/climatologicos/inventarioestaciones/todasestaciones/?api_key="
        private val COMPLETE_URL = URL+API_KEY
    }

    fun execute(): StationsResult {

        val responseAemetCallStr = GetAemetCallResult(COMPLETE_URL, okHttpSslClient)

        val mapper = jacksonObjectMapper()
        val stationsForecastList = mapper.readValue<List<Station>>(responseAemetCallStr)

        return StationsResult(stationsForecastList)
    }
}

fun GetAemetCallResult(url: String, okHttpSslClient: OkHttpClient, gson: Gson = Gson()): Reader {

    //First call
    val requestFirstCall = Request.Builder()
            .url(url)
            .build()


    val responseFirstCallStr = okHttpSslClient.newCall(requestFirstCall).execute().body()?.charStream()
    val pastForecastFirstCallResult : AemetFirstCallResult = gson.fromJson(responseFirstCallStr, AemetFirstCallResult::class.java)

    //Second call
    val requestFinalCall = Request.Builder()
            .url(pastForecastFirstCallResult.datos)
            .build()

    return okHttpSslClient.newCall(requestFinalCall).execute().body()?.charStream()!!
}