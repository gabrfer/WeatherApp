package fer.kotlin.weatherapp.ui.activities.earthforecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.ui.activities.earthforecast.actual.ActualForecastActivity
import fer.kotlin.weatherapp.ui.activities.earthforecast.past.MainHistoricalActivity
import kotlinx.android.synthetic.main.activity_main_earth_forecast.*
import org.jetbrains.anko.startActivity

class MainEarthForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_earth_forecast)

        linearLayoutPresent.setOnClickListener {
            startActivity<ActualForecastActivity>()
        }

        linearLayoutPast.setOnClickListener {
            startActivity<MainHistoricalActivity>()
        }

    }
}
