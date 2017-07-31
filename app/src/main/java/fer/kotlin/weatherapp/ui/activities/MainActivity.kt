package fer.kotlin.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import fer.kotlin.weatherapp.ui.adapters.ForecastListAdapter
import fer.kotlin.weatherapp.data.ForecastRequest
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.commands.RequestForecastCommand
import fer.kotlin.weatherapp.domain.model.Forecast
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val listDays = listOf(
        "Mon 6/23 - Sunny - 31/17",
        "Tue 6/24 - Foggy - 21/8",
        "Wed 6/25 - Cloudy - 22/17",
        "Thurs 6/26 - Rainy - 18/11",
        "Fri 6/27 - Foggy - 21/10",
        "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
        "Sun 6/29 - Sunny - 20/7"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var forecastList = findViewById(R.id.forecast_list) as RecyclerView
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("48003").execute()
            uiThread { forecastList.adapter = ForecastListAdapter(result,
                    object : ForecastListAdapter.OnItemClickListener{
                        override fun invoke(forecast: Forecast) {
                            toast(forecast.date)
                        }
                    })
            }
        }
    }
}
