package fer.kotlin.weatherapp.ui.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.pojo.TabDetails
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryCurrent
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryDays
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryDetails
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryHours
import fer.kotlin.weatherapp.utils.ObservableDsForecastCurrently
import fer.kotlin.weatherapp.utils.ObservableDsForecastDaily
import fer.kotlin.weatherapp.utils.ObservableDsForecastHourly
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

import java.util.ArrayList



class ViewPagerAdapter(manager: FragmentManager, ctx: Context) : FragmentPagerAdapter(manager) {

    private val context = ctx
    private val tabs = ArrayList<TabDetails>()

    override fun getCount(): Int = tabs.size
    override fun getItem(position:Int):Fragment = tabs[position].fragment
    fun addFragment(tab: TabDetails) {
        tabs.add(tab)
    }
    override fun getPageTitle(position:Int):CharSequence = tabs[position].tabName

    fun populateViewPager(forecastCurrently: ObservableDsForecastCurrently, forecastHourly: ObservableDsForecastHourly,
                                  forecastDaily: ObservableDsForecastDaily) {

        var tab:TabDetails

        val argumentsCurrent = Bundle()
        val currentFragment = FragmentEntryCurrent()
        argumentsCurrent.putSerializable("CURRENT_FORECAST", forecastCurrently)
        currentFragment.arguments = argumentsCurrent

        val argumentsDetails = Bundle()
        val detailsFragment = FragmentEntryDetails()
        //argumentsDetails.putParcelable("CURRENT_FORECAST_DETAILS", forecast!!.getDsForecast()!!.currently)
        argumentsDetails.putSerializable("CURRENT_FORECAST", forecastDaily)
        detailsFragment.arguments = argumentsDetails

        val argumentsHours = Bundle()
        val hoursFragment = FragmentEntryHours()
        //argumentsHours.putParcelableArrayList("LIST_HOURS_FORECAST", ArrayList<DsForecastHourly>(forecast!!.getDsForecast()!!.hourly))
        argumentsHours.putSerializable("CURRENT_FORECAST", forecastHourly)
        hoursFragment.arguments = argumentsHours

        val argumentsDays = Bundle()
        val daysFragment = FragmentEntryDays()
        //argumentsDays.putParcelableArrayList("LIST_DAYS_FORECAST", ArrayList<DsForecastDaily>(forecast!!.getDsForecast()!!.daily))
        argumentsDays.putSerializable("CURRENT_FORECAST", forecastDaily)
        daysFragment.arguments = argumentsDays

        tab = TabDetails(context.resources.getText(R.string.tab_main_actual_text) as String, currentFragment)
        this.addFragment(tab)
        tab = TabDetails(context.resources.getText(R.string.tab_main_detail_text) as String, detailsFragment)
        this.addFragment(tab)
        tab = TabDetails(context.resources.getText(R.string.tab_main_hourly_text) as String, hoursFragment)
        this.addFragment(tab)
        tab = TabDetails(context.resources.getText(R.string.tab_main_daily_text) as String, daysFragment)
        this.addFragment(tab)
    }
}