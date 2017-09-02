package fer.kotlin.weatherapp.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fer.kotlin.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPast.setOnClickListener {
            startActivity<HistoricalForecastActivity>()
        }

        btnActual.setOnClickListener {
            startActivity<ActualForecastActivity>()
        }
    }
}
