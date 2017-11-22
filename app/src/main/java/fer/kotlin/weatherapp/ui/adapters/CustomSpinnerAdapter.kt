package fer.kotlin.weatherapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import android.widget.BaseAdapter
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.ListType
import fer.kotlin.weatherapp.domain.model.Station


/**
 * Created by Default on 16/09/2017.
 */
class CustomSpinnerAdapter(private val activity: Context, private val asr: List<Station>, private val dataType: ListType = ListType.Station)
    : BaseAdapter(), SpinnerAdapter {


    override fun getCount(): Int {
        return asr.size
    }

    override fun getItem(i: Int): Any {
        return asr[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val txt = TextView(activity)
        txt.setPadding(16, 16, 16, 16)
        txt.textSize = 18f
        txt.gravity = Gravity.CENTER_VERTICAL
        txt.text = if (dataType == ListType.Provincia) asr[position].provincia else asr[position].nombre
        txt.setTextColor(Color.parseColor("#000000"))
        return txt
    }

    override fun getView(i: Int, view: View?, viewgroup: ViewGroup?): View {
        val txt = TextView(activity)
        txt.gravity = Gravity.LEFT
        txt.setPadding(16, 16, 16, 16)
        txt.textSize = 16f
        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0)
        txt.text = if (dataType == ListType.Provincia) asr[i].provincia else asr[i].nombre
        txt.setTextColor(Color.parseColor("#000000"))
        return txt
    }

    override fun getItemViewType(position: Int): Int {
        return android.R.layout.simple_spinner_dropdown_item
    }
}