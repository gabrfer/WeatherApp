package fer.kotlin.weatherapp.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import fer.kotlin.weatherapp.ui.fragments.twitter.FragmentTwitterMeteo
import fer.kotlin.weatherapp.ui.fragments.twitter.FragmentTwitterScience


class Twitter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val pageCount = 2
    private val tabTitles = arrayOf("Meteo", "Ciencia", "Otros")

    override fun getCount(): Int = pageCount

    override fun getItem(index: Int): Fragment? {

        when (index) {
            0 ->
                // Top Rated fragment activity
                return FragmentTwitterMeteo()
            1 ->
                // Games fragment activity
                return FragmentTwitterScience()
        }

        return null
    }

    override fun getPageTitle(position: Int): CharSequence =// Generate title based on item position
            tabTitles[position]

}