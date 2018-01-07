package fer.kotlin.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecastHourly
import fer.kotlin.weatherapp.extensions.UnixToTime
import fer.kotlin.weatherapp.extensions.ctx
import fer.kotlin.weatherapp.utils.loadForecastIconUrl
import kotlinx.android.synthetic.main.hour_forecast_item.view.*


class ForecastHoursAdapter(private val forecastList: ArrayList<DsForecastHourly>): RecyclerView.Adapter<ForecastHoursAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.hour_forecast_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: DsForecastHourly) {
            with(forecast) {

                itemView.txtHour.text = forecast.time.UnixToTime()
                itemView.imgIconWeather.loadForecastIconUrl(forecast.icon, ctx = itemView.ctx)
                itemView.txtTemp.text = """${String.format("%.1f", forecast.temperature.toDouble())}º"""
                itemView.txtRain.text = """${String.format("%.2f", forecast.precipIntensity.toDouble())} mm"""
                itemView.txtWindSpeed.text = """${String.format("%.1f", forecast.windSpeed.toDouble())} kmh"""
                //itemView.progresBarProbability.progress = (forecast.precipProbability.toDouble() * 100).toInt()
                itemView.txtRain.text = """${(forecast.precipProbability.toDouble() * 100).toInt()}%"""

                /* Expandable layout */
                itemView.txtTempExp.text = """${String.format("%.1f", forecast.temperature.toDouble())}º"""
                itemView.txtRainExp.text = String.format("%.2f", forecast.precipIntensity.toDouble())
                itemView.txtRainProbExp.text = forecast.precipProbability
                itemView.txtWindExp.text = String.format("%.2f", forecast.windSpeed.toDouble())
                itemView.txtHumidityExp.text = String.format("%.2f", forecast.humidity.toDouble())
                itemView.txtPressureExp.text = String.format("%.2f", forecast.pressure.toDouble())
                itemView.txtUvIndexExp.text = forecast.uvIndex

                itemView.setOnClickListener {
                    if (itemView.layoutExpandable.visibility == View.GONE) {
                        itemView.layoutExpandable.visibility = View.VISIBLE
                    }
                    else { itemView.layoutExpandable.visibility = View.GONE }
                }
            }
        }
    }

}