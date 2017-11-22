package fer.kotlin.weatherapp.ui.activities.earthforecast.actual

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.extensions.color
import fer.kotlin.weatherapp.extensions.textColor
import fer.kotlin.weatherapp.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail_actual.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActualActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActualActivity:id"
        val CITY_NAME = "DetailActualActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_actual)

        title = intent.getStringExtra(CITY_NAME)

        doAsync {
            //val result = RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute()
            //uiThread { bindForecast(result) }
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}
