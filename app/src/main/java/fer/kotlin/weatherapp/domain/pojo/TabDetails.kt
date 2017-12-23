package fer.kotlin.weatherapp.domain.pojo

import android.support.v4.app.Fragment
import fer.kotlin.weatherapp.ui.activities.MainActivity



class TabDetails(tabName:String, fragment:Fragment) {

    var tabName:String
    var fragment: Fragment

    init{
        this.tabName = tabName
        this.fragment = fragment
    }
}