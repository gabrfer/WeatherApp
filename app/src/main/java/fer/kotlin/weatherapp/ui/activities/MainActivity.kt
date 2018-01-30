package fer.kotlin.weatherapp.ui.activities

import android.Manifest
import android.app.Activity
import android.os.Bundle
import fer.kotlin.weatherapp.R
import kotlinx.android.synthetic.main.content_main.*
import android.support.v4.app.ActivityCompat
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.net.Uri
import android.os.AsyncTask
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import fer.kotlin.weatherapp.BuildConfig
import fer.kotlin.weatherapp.domain.commands.DsRequestActualForecast
import fer.kotlin.weatherapp.domain.model.DsForecast
import kotlin.collections.ArrayList
import android.view.MenuItem
import android.location.Geocoder
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import fer.kotlin.weatherapp.extensions.showSnackbar
import fer.kotlin.weatherapp.services.LocationReceiver
import fer.kotlin.weatherapp.services.LocationService
import fer.kotlin.weatherapp.ui.adapters.ViewPagerAdapter
import fer.kotlin.weatherapp.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.io.IOException
import java.util.*
import org.jetbrains.anko.startActivity



class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback, PermissionUtils.PermissionResultCallback,
                                          NavigationView.OnNavigationItemSelectedListener  {

    private lateinit var mSectionsPagerAdapter:ViewPagerAdapter

    private lateinit var permissionUtils:PermissionUtils
    private var permissions:ArrayList<String> = ArrayList()
    private var mLUService: LocationService? = null
    private var myReceiver:LocationReceiver? = null
    private var mLastLocation: Location? = null
    private var currentLatitude : String = ""
    private var currentLongitude : String = ""
    private var currentLocationName : String = ""

    private lateinit var forecast: ObservableDsForecast
    private lateinit var forecastCurrently: ObservableDsForecastCurrently
    private lateinit var forecastHourly: ObservableDsForecastHourly
    private lateinit var forecastDaily: ObservableDsForecastDaily

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast = ObservableDsForecast()
        forecastCurrently = ObservableDsForecastCurrently()
        forecastHourly = ObservableDsForecastHourly()
        forecastDaily = ObservableDsForecastDaily()

        permissionUtils = PermissionUtils(this@MainActivity)
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        permissionUtils.checkPermission(permissions,"Explain here why the app needs permissions",1)

        initNavigationDrawer()

        this@MainActivity.title = ""
        txtToolbarCity.text = "Bilbao"

        mSectionsPagerAdapter = ViewPagerAdapter(supportFragmentManager, this@MainActivity)
    }

    override fun onResume() {
        super.onResume()

        if (mLUService == null) { mLUService = LocationService() }

        if (myReceiver == null) { myReceiver = LocationReceiver() }

        registerReceiver(myReceiver, IntentFilter("location_update"))
    }

    /* Navigation drawer */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        displaySelectedFragment(item.itemId)
        return true
    }

    private fun displaySelectedFragment(menuItemId:Int) {

        var activity: Activity? = null
        val bundle = Bundle()/* Creates the fragments and sets it to ViewPager */
        //To change body of created functions use File | Settings | File Templates.
        /* Permission managing */
        // Build intent that displays the App settings screen.
        // Build intent that displays the App settings screen.
        /* Permission managing */
        when (menuItemId) {
            R.id.home ->  Log.d("", "Activity Main")
            R.id.twitterGroup -> startActivity<TwitterActivity>()
            R.id.twitterMeteo -> startActivity<TwitterActivity>()
            R.id.twitterScience -> startActivity<TwitterActivity>()
            else -> Log.d("", "Activity nothing")
        }

        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun initNavigationDrawer() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onOptionsItemSelected(item:MenuItem):Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
    /* Navigation drawer */

    fun changeLocation(location: Location, title: String) {
        mLastLocation = location
        currentLatitude = location.latitude.toString()
        currentLongitude = location.longitude.toString()

        try
        {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            currentLocationName = if (addresses.size > 0) { addresses[0].locality?:"Bilbao" }
                                  else "Bilbao"

        }
        catch (e: IOException) {
            e.printStackTrace()
        }

        AsyncTaskExample().execute()
    }

    /* Permission managing */
    override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<String>, grantResults:IntArray) {
        permissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun partialPermissionGranted(request_code: Int, granted_permissions: java.util.ArrayList<String>) {
        this.showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                View.OnClickListener {
                    // Build intent that displays the App settings screen.
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package",
                            BuildConfig.APPLICATION_ID, null)
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                })
    }

    override fun permissionDenied(request_code: Int) {
        showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                View.OnClickListener {
                    // Build intent that displays the App settings screen.
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri = Uri.fromParts("package",
                            BuildConfig.APPLICATION_ID, null)
                    intent.data = uri
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                })
    }

    override fun neverAskAgain(request_code: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun permissionGranted(request_code: Int) {
        if(mLUService == null) {
            mLUService = LocationService()
            startService(Intent(this, LocationService::class.java))
        }
    }
    /* Permission managing */

    /* Creates the fragments and sets it to ViewPager */
    private fun populateViewPager() {
        mSectionsPagerAdapter.populateViewPager(forecastCurrently, forecastHourly, forecastDaily)
        viewPager.adapter = mSectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    @Suppress("NAME_SHADOWING")
        inner class AsyncTaskExample: AsyncTask<String, DsForecast?, DsForecast?>() {

            override fun onPreExecute() {
                super.onPreExecute()
                progress_bar.visibility = View.VISIBLE
            }

            override fun doInBackground(vararg p0: String?): DsForecast? {

                var result: DsForecast? = null

                try {
                    result = DsRequestActualForecast(currentLatitude, currentLongitude).execute()
                } catch(Ex: Exception) {
                    Log.d("", "Error in doInBackground " + Ex.message)
                }
                return result
            }

            override fun onPostExecute(result: DsForecast?) {
                super.onPostExecute(result)

                if (result != null) {
                    forecast.setDsForecast(result)
                    forecastCurrently.setDsForecastCurrently(result.currently!!)
                    forecastHourly.setDsForecastHourly(ArrayList(result.hourly))
                    forecastDaily.setDsForecastDaily(ArrayList(result.daily))

                    if (mSectionsPagerAdapter.count == 0) { populateViewPager() }
                }

                progress_bar.visibility = View.GONE
            }
        }
}