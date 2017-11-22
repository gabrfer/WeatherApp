package fer.kotlin.weatherapp.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import fer.kotlin.weatherapp.ui.fragments.tab_general
import fer.kotlin.weatherapp.ui.fragments.tab_rain_wind
import fer.kotlin.weatherapp.ui.fragments.tab_sun_moon


class TabsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val pageCount = 3
    private val tabTitles = arrayOf("General", "Lluvia/Viento", "Sol/Luna")

    override fun getCount(): Int = pageCount

    override fun getItem(index: Int): Fragment? {

        when (index) {
            0 ->
                // Top Rated fragment activity
                return tab_general()
            1 ->
                // Games fragment activity
                return tab_rain_wind()
            2 ->
                // Movies fragment activity
                return tab_sun_moon()
        }

        return null
    }

    override fun getPageTitle(position: Int): CharSequence =// Generate title based on item position
            tabTitles[position]

}