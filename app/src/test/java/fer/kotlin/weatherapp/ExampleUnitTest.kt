package fer.kotlin.weatherapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {

        val API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaG9wZXJkaW5hbmRAZ21haWwuY29tIiwianRpIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwiZXhwIjoxNTEwMzkzMzAwLCJpc3MiOiJBRU1FVCIsImlhdCI6MTUwMjYxNzMwMCwidXNlcklkIjoiYjlhNjI1OTEtYjJkMS00OWZkLTk0YjItNWI5ZDFmNDM2YjYxIiwicm9sZSI6IiJ9.0Y9dUdWDDvnWW07gVHjKtlMKfdaqvY-6IQ-kLOgBGjE"
        val URL = "https://opendata.aemet.es/opendata/api/valores/climatologicos/diarios/datos/fechaini/_dateFrom/fechafin/_dateTo/estacion/_station/?"
        val COMPLETE_URL = "$URL&api_key=$API_KEY"

        val finalURL: String = COMPLETE_URL.replace("_dateFrom", "2000-02-01T00:00:00UTC").replace("_dateTo", "2000-02-27T23:59:59UTC").replace("_station", "1082")
        val forecastJsonStr = java.net.URL(finalURL).readText()

        println(forecastJsonStr)

    }
}