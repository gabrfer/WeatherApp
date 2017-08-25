package fer.kotlin.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import fer.kotlin.weatherapp.ui.adapters.ForecastListAdapter
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.commands.PastRequestForecastCommand
import fer.kotlin.weatherapp.domain.commands.RequestForecastCommand
import fer.kotlin.weatherapp.ui.adapters.PastForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            /*val result = RequestForecastCommand(48003).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                title = "${result.city} (${result.country})"
            }*/

            val result = PastRequestForecastCommand("1082", "2000-02-01T00:00:00UTC", "2000-02-27T23:59:59UTC").execute()
            uiThread {
                val adapter = PastForecastListAdapter(result) {}
                forecastList.adapter = adapter
                title = "BILBAO - HACE MUCHO"
            }
        }
    }
}
