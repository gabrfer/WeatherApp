package fer.kotlin.weatherapp.services

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.location.Location
import fer.kotlin.weatherapp.ui.activities.MainActivity

class LocationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val location = intent.getParcelableExtra<Location>(LocationService.LOCATION_UPDATE)
        if (location != null) {
            sendDataToCurrentActivity(location, context)
        }
    }

    private fun sendDataToCurrentActivity(location: Location, context: Context) {
        if (context.javaClass == MainActivity::class.java) (context as MainActivity).changeLocation(location, "My location")
    }
}