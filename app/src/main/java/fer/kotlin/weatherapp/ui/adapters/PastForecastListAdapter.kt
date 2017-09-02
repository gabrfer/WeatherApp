package fer.kotlin.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.PastForecast
import fer.kotlin.weatherapp.domain.model.PastForecastList
import fer.kotlin.weatherapp.extensions.ctx
import fer.kotlin.weatherapp.extensions.toDateString
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by Default on 15/08/2017.
 */
class PastForecastListAdapter(val pastForecastList: PastForecastList, val itemClick: (PastForecast) -> Unit):
        RecyclerView.Adapter<PastForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)

        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(pastForecastList[position])
    }

    override fun getItemCount(): Int = pastForecastList.size

    class ViewHolder(view: View, val itemClick: (PastForecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: PastForecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = fecha
                itemView.description.text = nombre
                itemView.maxTemperature.text = "${tmax}º"
                itemView.minTemperature.text = "${tmin}º"
                //itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}