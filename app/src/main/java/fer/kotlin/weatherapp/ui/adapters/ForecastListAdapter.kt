package fer.kotlin.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.Forecast
import fer.kotlin.weatherapp.domain.model.ForecastList
import fer.kotlin.weatherapp.extensions.ctx
import fer.kotlin.weatherapp.extensions.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*


class ForecastListAdapter(private val forecastList: ForecastList, private val itemClick: (Forecast) -> Unit):
                                    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)

            return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size

    class ViewHolder(view: View, private val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date.toDateString()
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}