package fer.kotlin.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecastDaily
import fer.kotlin.weatherapp.extensions.DateUnixToDayOfWeek
import fer.kotlin.weatherapp.extensions.DateUnixToNormal
import fer.kotlin.weatherapp.extensions.ctx
import fer.kotlin.weatherapp.utils.loadForecastIconUrl
import kotlinx.android.synthetic.main.day_forecast_item.view.*


class ForecastDaysAdapter(private val forecastList: ArrayList<DsForecastDaily>): RecyclerView.Adapter<ForecastDaysAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.ctx).inflate(R.layout.day_forecast_item, parent, false)

            return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: DsForecastDaily) {
            with(forecast) {
                itemView.txtDate.text = forecast.time.DateUnixToNormal()
                itemView.txtDay.text = forecast.time.DateUnixToDayOfWeek()

                itemView.imgIconWeather.loadForecastIconUrl(forecast.icon, ctx = itemView.ctx)

                itemView.txtTempMax.text = """${String.format("%.1f", forecast.temperatureMax.toDouble())}º"""
                itemView.txtTempMax.text = """${String.format("%.1f", forecast.temperatureMin.toDouble())}º"""

                itemView.txtRain.text = """${String.format("%.2f", forecast.precipIntensity.toDouble())} mm"""
                itemView.txtWindSpeed.text = """${String.format("%.1f", forecast.windSpeed.toDouble())} kmh"""

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