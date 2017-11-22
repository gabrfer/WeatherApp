package fer.kotlin.weatherapp.ui.activities.earthforecast.past


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_historical.*
import fer.kotlin.weatherapp.ui.adapters.TabsPagerAdapter
import android.support.design.widget.TabLayout
import fer.kotlin.weatherapp.domain.commands.DsRequestPastForecast
import fer.kotlin.weatherapp.extensions.UnixToTime
import kotlinx.android.synthetic.main.fragment_tab_general.*
import kotlinx.android.synthetic.main.fragment_tab_rain_wind.*
import kotlinx.android.synthetic.main.fragment_tab_sun_moon.*
import android.os.AsyncTask
import android.util.Log
import android.view.View
import fer.kotlin.weatherapp.domain.model.DsForecast
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import fer.kotlin.weatherapp.utils.*


class DetailHistoricalActivity : AppCompatActivity() {

    // record the angle turned of the compass picture
    private var degreeStart: Float = 0f

    companion object {
        val ID = "DetailHistoricalActivity:id"
        val CITY_NAME = "DetailHistoricalActivity:cityName"
        val LATITUD = "DetailHistoricalActivity:latitud"
        val LONGITUD = "DetailHistoricalActivity:longitud"
        val FECHA = "DetailHistoricalActivity:fecha"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(fer.kotlin.weatherapp.R.layout.activity_detail_historical)

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        pager.adapter = TabsPagerAdapter(supportFragmentManager)
        pager.offscreenPageLimit = 3

        // Give the TabLayout the ViewPager
        val tabLayout: TabLayout = findViewById(fer.kotlin.weatherapp.R.id.tabLayout) as TabLayout
        tabLayout.setupWithViewPager(pager)

        AsyncTaskExample().execute()

        /*val latitud = intent.getStringExtra(LATITUD)
        val longitud = intent.getStringExtra(LONGITUD)
        val fecha = intent.getIntArrayExtra(FECHA)

        doAsync {
            val result = DsRequestPastForecast(latitud, longitud, fecha).execute()
            uiThread {
                imgIcon.loadForecastIconUrl(result.icon, ctx = this@DetailHistoricalActivity)

                txtTempMax.text = """${String.format("%.1f", result.temperatureMax.toDouble())}º"""
                txtTempMin.text = """${String.format("%.1f", result.temperatureMin.toDouble())}º"""

                txtPrediccionDesc.text = result.summary

                txtHoraTempMax.text = result.temperatureMaxTime.UnixToTime()
                txtHoraTempMin.text = result.temperatureMinTime.UnixToTime()

                txtHumedad.text = """${(result.humidity.toDouble() * 100)}%"""
                txtPresion.text = """${result.pressure} hPa"""
            }
        }*/
    }

    @Suppress("NAME_SHADOWING")
    inner class AsyncTaskExample: AsyncTask<String, DsForecast?, DsForecast?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            pager.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): DsForecast? {

            var result: DsForecast? = null

            try {

                val latitud = intent.getStringExtra(LATITUD)
                val longitud = intent.getStringExtra(LONGITUD)
                val fecha = intent.getIntArrayExtra(FECHA)

                result = DsRequestPastForecast(latitud, longitud, fecha).execute()

            } catch(Ex: Exception) {
                Log.d("", "Error in doInBackground " + Ex.message)
            }
            return result
        }

        override fun onPostExecute(result: DsForecast?) {
            super.onPostExecute(result)

            if (result != null) {
                loadTabGeneral(result)
                loadTabRAinWind(result)
                loadTabSunMoon(result)
            }

            pager.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE

        }

        private fun loadTabGeneral(result: DsForecast) {
            with (result) {
                imgIcon.loadForecastIconUrl(icon, ctx = this@DetailHistoricalActivity)

                txtTempMax.text = """${String.format("%.1f", temperatureMax.toDouble())}º"""
                txtTempMin.text = """${String.format("%.1f", temperatureMin.toDouble())}º"""

                txtPrediccionDesc.text = summary

                txtHoraTempMax.text = temperatureMaxTime.UnixToTime()
                txtHoraTempMin.text = temperatureMinTime.UnixToTime()

                txtHumedad.text = """${(humidity.toDouble() * 100)}%"""
                txtPresion.text = """$pressure hPa"""
            }
        }

        private fun loadTabRAinWind(result: DsForecast) {
            with (result) {
                imgViewRain.loadRainTypeIconUrl(precipType, ctx = this@DetailHistoricalActivity)

                txtRainType.text = if (precipIntensity != "") getPrecipType(precipType) else "SIN LLUVIA"
                txtIntensity.text = if (precipIntensity != "") """$precipIntensity mm/h""" else "[Sin datos]"

                txtIntensityMax.text = if (precipIntensityMax != "") """$precipIntensityMax mm/h""" else "[Sin datos]"
                txtHoraIntensityMax.text = precipIntensityMaxTime.UnixToTime()

                txtWindSpeed.text = if (windSpeed != "") """$windSpeed m/s""" else "[Sin datos]"

                //Compass configure
                //get angle around the z-axis rotated
                if (windBearing != "") {
                    //TextBox with the degrees value
                    txtWindBearing.text = if (windBearing != "") """$windBearing º""" else "[Sin datos]"

                    //Compass
                    val degree = Math.round(windBearing.toFloat()).toFloat()

                    // rotation animation - reverse turn degree degrees
                    val ra = RotateAnimation(
                            degreeStart,
                            -degree,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f)
                    // set the compass animation after the end of the reservation status
                    ra.fillAfter = true
                    // set how long the animation for the compass image will take place
                    ra.duration = 210
                    // Start animation of compass image
                    imageViewCompass.startAnimation(ra)
                    //degreeStart = -degree

                    imageViewCompass.setOnClickListener {
                        // rotation animation - reverse turn degree degrees
                        val ra = RotateAnimation(
                                -degree,
                                degreeStart,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f)
                        // set the compass animation after the end of the reservation status
                        ra.fillAfter = true
                        // set how long the animation for the compass image will take place
                        ra.duration = 1300
                        // Start animation of compass image
                        imageViewCompass.startAnimation(ra)

                        val ra2 = RotateAnimation(
                                degreeStart,
                                -degree,
                                Animation.RELATIVE_TO_SELF, 0.5f,
                                Animation.RELATIVE_TO_SELF, 0.5f)
                        // set the compass animation after the end of the reservation status
                        ra2.fillAfter = true
                        // set how long the animation for the compass image will take place
                        ra2.duration = 1300
                        // Start animation of compass image
                        imageViewCompass.startAnimation(ra2)
                    }
                }
            }
        }

        private fun loadTabSunMoon(result: DsForecast) {
            with (result) {

                txtSunriseTime.text = sunriseTime.UnixToTime()
                txtSunsetTime.text = sunsetTime.UnixToTime()

                txtUvIndex.text = uvIndex
                //txtUvIndex.setBackgroundColor(getUvIndexColor(uvIndex = uvIndex.toInt()))
                txtUvIndex.setBackgroundResource(getUvIndexColor(uvIndex = uvIndex.toInt()))


                txtHoraMaxUv.text = uvIndexTime.UnixToTime()


                txtFaseLunar.text = moonPhase
                imgViewFaseLunar.loadMoonPhaseIconUrl(moonPhase.toDouble(), ctx = this@DetailHistoricalActivity)
            }

        }

    }

}
