package fer.kotlin.weatherapp.ui.activities.earthforecast.past

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.ui.adapters.PastForecastListAdapter
import fer.kotlin.weatherapp.utils.CustomSSLSocketFactory
import kotlinx.android.synthetic.main.activity_historical_forecast.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class HistoricalForecastActivity : AppCompatActivity() {

    companion object {
        val START_DATE = "HistoricalForecastActivity:startDate"
        val END_DATE = "HistoricalForecastActivity:endDate"
        val STATION = "HistoricalForecastActivity:station"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historical_forecast)

        val okHttpSslClient: OkHttpClient = CustomSSLSocketFactory.getSSLSocketFactory(applicationContext)!!

        val startDate = intent.getStringExtra(START_DATE)
        val endDate = intent.getStringExtra(END_DATE)
        val station = intent.getStringExtra(STATION)

        forecastList.layoutManager = LinearLayoutManager(this)
        progress_bar.visibility = View.VISIBLE

        doAsync {
            /*val result = PastRequestForecastCommand(okHttpSslClient, station, startDate, endDate).execute()
            uiThread {
                val adapter = PastForecastListAdapter(result) {
                    startActivity<DetailHistoricalActivity>(DetailHistoricalActivity.ID to it.id, DetailHistoricalActivity.CITY_NAME to it.city)
                }
                forecastList.adapter = adapter
                progress_bar.visibility = View.GONE
                title = "BILBAO - HACE MUCHO"
            }*/
        }
    }
}
