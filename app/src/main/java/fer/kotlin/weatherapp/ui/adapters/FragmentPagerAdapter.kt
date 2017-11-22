package fer.kotlin.weatherapp.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import fer.kotlin.weatherapp.ui.fragments.PageFragment



class PageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    var items: MutableList<PageFragment> = ArrayList()

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Fragment = items[position]

    fun setInitialItems(listItems: MutableList<PageFragment>) {
        items = listItems
    }

    fun addItem(item: PageFragment) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyDataSetChanged()
    }
}