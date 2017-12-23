package fer.kotlin.weatherapp.ui.activities

import android.Manifest
import android.os.Bundle
import fer.kotlin.weatherapp.R
import com.google.android.gms.common.ConnectionResult
import kotlinx.android.synthetic.main.content_main.*
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import android.app.AlertDialog
import android.content.Intent
import android.location.Location
import android.net.Uri
import android.os.AsyncTask
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import fer.kotlin.weatherapp.BuildConfig
import fer.kotlin.weatherapp.domain.commands.DsRequestActualForecast
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.DsForecastDaily
import fer.kotlin.weatherapp.domain.model.DsForecastHourly
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryCurrent
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryDays
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryHours
import kotlin.collections.ArrayList
import android.support.v4.view.ViewPager
import android.view.MenuItem
import fer.kotlin.weatherapp.ui.adapters.ViewPagerAdapter
import android.location.Geocoder
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.LayoutInflater
import android.view.ViewGroup
import fer.kotlin.weatherapp.R.string.tab_main_actual_text
import fer.kotlin.weatherapp.domain.pojo.TabDetails
import fer.kotlin.weatherapp.extensions.BottomNavigationViewBehavior
import fer.kotlin.weatherapp.extensions.BottomNavigationViewHelper
import fer.kotlin.weatherapp.ui.fragments.FragmentEntryDetails
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.find
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    private val PLAY_SERVICES_RESOLUTION_REQUEST = 9000

    private lateinit var mSectionsPagerAdapter:SectionsPagerAdapter
    private var prevMenuItem: MenuItem? = null
    private val fragments = ArrayList<Fragment>(4)

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLastLocation: Location? = null
    private var currentLatitude : String = ""
    private var currentLongitude : String = ""
    private var currentLocationName : String = ""

    private var forecast: DsForecast? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationDrawer()

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        this@MainActivity.title = ""
        txtToolbarCity.text = "Bilbao"

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        /*val layoutParams:CoordinatorLayout.LayoutParams = bottomNav.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationViewBehavior()

        BottomNavigationViewHelper.disableShiftMode(bottomNav)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottombaritem_current -> {
                    viewpager.currentItem = 0
                    //switchFragment(0)
                    //true
                }
                R.id.bottombaritem_detail -> {
                    viewpager.currentItem = 1
                    //switchFragment(1)
                    //true
                }
                R.id.bottombaritem_hours -> {
                    viewpager.currentItem = 2
                    //switchFragment(2)
                    //true
                }
                R.id.bottombaritem_week -> {
                    viewpager.currentItem = 3
                    //switchFragment(3)
                    //true
                }
            }
            false
        }*/

       /* viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNav.menu.getItem(0).isChecked = false
                }

                bottomNav.menu.getItem(position).isChecked = true
                prevMenuItem = bottomNav.menu.getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })*/
    }

    override fun onStart() {
        super.onStart()

        if (!checkPermissions()) {
            requestPermissions()
        } else {
            getLastLocation()
        }
    }

    override fun onResume() {
        super.onResume()

        if (!checkPlayServices()) {
            AlertDialog.Builder(this@MainActivity)
                    .setMessage("Please install Google Play services.")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        displaySelectedFragment(item.itemId)
        return true
    }

    private fun displaySelectedFragment(menuItemId:Int) {
/*        val fragment:Fragment = null
        when (menuItemId) {
            R.id.nav_store -> fragment = StoreFragment()
            R.id.nav_chat -> fragment = ChatFragment()
            R.id.nav_settings -> fragment = SettingsFragment()
            else -> {}
        }
        //replace the current fragment
        if (fragment != null)
        {
            val ft = getSupportFragmentManager().beginTransaction()
            ft.replace(R.id.container_frame, fragment)
            ft.commit()
        }*/

        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun initNavigationDrawer() {

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show()
            } else
                finish()

            return false
        }
        return true
    }

    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     * <p>
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressWarnings("MissingPermission")
    private fun getLastLocation() {
        mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && task.result != null) {
                        mLastLocation = task.result
                        currentLatitude = mLastLocation?.latitude.toString()
                        currentLongitude = mLastLocation?.longitude.toString()

                        try
                        {
                            val geocoder = Geocoder(this, Locale.getDefault())
                            val addresses = geocoder.getFromLocation(mLastLocation!!.latitude, mLastLocation!!.longitude, 1)
                            currentLocationName = addresses[0].locality?:"Bilbao"
                        }
                        catch (e: IOException) {
                            e.printStackTrace()
                        }

                        AsyncTaskExample().execute()

                    } else {
                        Log.w("", "getLastLocation:exception", task.exception)
                        showSnackbar(getString(R.string.no_location_detected))
                    }
                }
    }

    /**
     * Return the current state of the permissions needed.
     */
    private fun checkPermissions(): Boolean {
        val permissionState: Int = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(this@MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't ch 0eck the "Don't ask again" checkbox.
        if (shouldProvideRationale)
        {
            Log.i("", "Displaying permission rationale to provide additional context.")
            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    View.OnClickListener {
                        // Request permission
                        startLocationPermissionRequest()
                    })
        }
        else
        {
            Log.i("", "Requesting permission")
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest()
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode:Int, permissions:Array<String>, grantResults:IntArray) {
        Log.i("", "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE)
        {
            when {
                grantResults.isEmpty() -> // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                    Log.i("", "User interaction was cancelled.")
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> // Permission granted.
                    getLastLocation()
                else -> // Permission denied.
                    // Notify the user via a SnackBar that they have rejected a core permission for the
                    // app, which makes the Activity useless. In a real app, core permissions would
                    // typically be best requested during a welcome-screen flow.
                    // Additionally, it is important to remember that a permission might have been
                    // rejected without asking the user for permission (device policy or "Never ask
                    // again" prompts). Therefore, a user interface affordance is typically implemented
                    // when permissions are denied. Otherwise, your app could appear unresponsive to
                    // touches or interactions which have required permissions.
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
        }
    }

    /**
     * Shows a {@link Snackbar} using {@code text}.
     *
     * @param text The Snackbar text.
     */
    private fun showSnackbar(text:String) {
        Snackbar.make(frame_fragmentholder, text, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Shows a {@link Snackbar}.
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId The text of the action item.
     * @param listener The listener associated with the Snackbar action.
     */
    private fun showSnackbar(mainTextStringId:Int, actionStringId:Int,
                             listener:View.OnClickListener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show()
    }



    private fun buildFragmentsList() {

        /*val adapter = ViewPagerAdapter(supportFragmentManager)

        val argumentsCurrent = Bundle()
        val currentFragment = FragmentEntryCurrent()
        argumentsCurrent.putString("CURRENT_LOCATION_NAME", currentLocationName)
        argumentsCurrent.putParcelable("CURRENT_FORECAST", forecast!!.currently)
        currentFragment.arguments = argumentsCurrent

        val argumentsDetails = Bundle()
        val detailsFragment = FragmentEntryDetails()
        argumentsDetails.putParcelable("CURRENT_FORECAST_DETAILS", forecast!!.currently)
        detailsFragment.arguments = argumentsDetails

        val argumentsHours = Bundle()
        val hoursFragment = FragmentEntryHours()
        argumentsHours.putParcelableArrayList("LIST_HOURS_FORECAST", ArrayList<DsForecastHourly>(forecast!!.hourly))
        hoursFragment.arguments = argumentsHours

        val argumentsDays = Bundle()
        val daysFragment = FragmentEntryDays()
        argumentsDays.putParcelableArrayList("LIST_DAYS_FORECAST", ArrayList<DsForecastDaily>(forecast!!.daily))
        daysFragment.arguments = argumentsDays

        adapter.addFragment(currentFragment)
        adapter.addFragment(detailsFragment)
        adapter.addFragment(hoursFragment)
        adapter.addFragment(daysFragment)
        viewpager.adapter = adapter*/
    }

    /**
     * A placeholder fragment containing a simple view.
     */

    class PlaceholderFragment:Fragment() {
        fun onCreateView(inflater: LayoutInflater, container: ViewGroup):View {
            return inflater.inflate(arguments.getInt(ARG_LAYOUT), container, false)
        }
        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_LAYOUT = "layout"
            /**
             * Returns a new instance of this fragment for the given layout.
             */
            fun newInstance(layout:Int):PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_LAYOUT, layout)
                fragment.arguments = args
                return fragment
            }
        }
    }

    override fun onOptionsItemSelected(item:MenuItem):Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Creates the fragments and sets it to ViewPager
     */
    private fun populateViewPager() {
        var tab:TabDetails

        val argumentsCurrent = Bundle()
        val currentFragment = FragmentEntryCurrent()
        argumentsCurrent.putString("CURRENT_LOCATION_NAME", currentLocationName)
        argumentsCurrent.putParcelable("CURRENT_FORECAST", forecast!!.currently)
        currentFragment.arguments = argumentsCurrent

        val argumentsDetails = Bundle()
        val detailsFragment = FragmentEntryDetails()
        argumentsDetails.putParcelable("CURRENT_FORECAST_DETAILS", forecast!!.currently)
        detailsFragment.arguments = argumentsDetails

        val argumentsHours = Bundle()
        val hoursFragment = FragmentEntryHours()
        argumentsHours.putParcelableArrayList("LIST_HOURS_FORECAST", ArrayList<DsForecastHourly>(forecast!!.hourly))
        hoursFragment.arguments = argumentsHours

        val argumentsDays = Bundle()
        val daysFragment = FragmentEntryDays()
        argumentsDays.putParcelableArrayList("LIST_DAYS_FORECAST", ArrayList<DsForecastDaily>(forecast!!.daily))
        daysFragment.arguments = argumentsDays

        tab = TabDetails(resources.getText(R.string.tab_main_actual_text) as String, currentFragment)
        mSectionsPagerAdapter.addFragment(tab)
        tab = TabDetails(resources.getText(R.string.tab_main_detail_text) as String, detailsFragment)
        mSectionsPagerAdapter.addFragment(tab)
        tab = TabDetails(resources.getText(R.string.tab_main_hourly_text) as String, hoursFragment)
        mSectionsPagerAdapter.addFragment(tab)
        tab = TabDetails(resources.getText(R.string.tab_main_daily_text) as String, daysFragment)
        mSectionsPagerAdapter.addFragment(tab)

        viewPager.adapter = mSectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

        //val adapter = ViewPagerAdapter(supportFragmentManager)

        /*val argumentsCurrent = Bundle()
        val currentFragment = FragmentEntryCurrent()
        argumentsCurrent.putString("CURRENT_LOCATION_NAME", currentLocationName)
        argumentsCurrent.putParcelable("CURRENT_FORECAST", forecast!!.currently)
        currentFragment.arguments = argumentsCurrent

        val argumentsDetails = Bundle()
        val detailsFragment = FragmentEntryDetails()
        argumentsDetails.putParcelable("CURRENT_FORECAST_DETAILS", forecast!!.currently)
        detailsFragment.arguments = argumentsDetails

        val argumentsHours = Bundle()
        val hoursFragment = FragmentEntryHours()
        argumentsHours.putParcelableArrayList("LIST_HOURS_FORECAST", ArrayList<DsForecastHourly>(forecast!!.hourly))
        hoursFragment.arguments = argumentsHours

        val argumentsDays = Bundle()
        val daysFragment = FragmentEntryDays()
        argumentsDays.putParcelableArrayList("LIST_DAYS_FORECAST", ArrayList<DsForecastDaily>(forecast!!.daily))
        daysFragment.arguments = argumentsDays

        adapter.addFragment(currentFragment)
        adapter.addFragment(detailsFragment)
        adapter.addFragment(hoursFragment)
        adapter.addFragment(daysFragment)
        viewpager.adapter = adapter*/
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    class SectionsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        private val tabs = ArrayList<TabDetails>()

        override fun getCount(): Int {
            return tabs.size
        }
        override fun getItem(position:Int):Fragment {
            return tabs[position].fragment
        }
        fun addFragment(tab: TabDetails) {
            tabs.add(tab)
        }
        override fun getPageTitle(position:Int):CharSequence {
            return tabs[position].tabName
        }
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
                forecast = result

                populateViewPager()
                //buildFragmentsList()
            }

            progress_bar.visibility = View.GONE

        }

    }
}



