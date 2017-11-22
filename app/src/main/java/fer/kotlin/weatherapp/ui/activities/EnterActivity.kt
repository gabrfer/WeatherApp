package fer.kotlin.weatherapp.ui.activities

import android.os.Bundle

import fer.kotlin.weatherapp.R
import android.widget.Toast
import fer.kotlin.weatherapp.ui.fragments.PageFragment
import android.support.v4.view.ViewPager
import fer.kotlin.weatherapp.ui.adapters.PageAdapter
import android.support.v4.app.FragmentActivity
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_enter.*
import android.os.Build
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationListener
import android.content.DialogInterface
import android.annotation.TargetApi
import android.app.AlertDialog
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.google.android.gms.common.GoogleApiAvailability
import fer.kotlin.weatherapp.domain.commands.DsRequestActualForecast
import fer.kotlin.weatherapp.domain.commands.DsRequestPastForecast
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.extensions.UnixToTime


class EnterActivity : FragmentActivity(), GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    lateinit var adapter: PageAdapter
    //lateinit var pager: ViewPager
    internal var previousPosition = 0

    var mLocation: Location? = null
    var mGoogleApiClient: GoogleApiClient? = null
    private val PLAY_SERVICES_RESOLUTION_REQUEST = 9000

    private lateinit var mLocationRequest: LocationRequest
    private val UPDATE_INTERVAL: Long = 15000  /* 15 secs */
    private val FASTEST_INTERVAL: Long = 5000 /* 5 secs */

    private var permissionsToRequest = ArrayList<String>()
    private val permissionsRejected = ArrayList<String>()
    private val permissions = ArrayList<String>()

    private val ALL_PERMISSIONS_RESULT = 101

    private var currentLatitude : String = ""
    private var currentLongitude : String = ""

    private fun startLocationUpdates() {
        mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = UPDATE_INTERVAL
        mLocationRequest.fastestInterval = FASTEST_INTERVAL
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(applicationContext, "Enable Permissions", Toast.LENGTH_LONG).show()
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this)


    }

    private fun hasPermission(permission: String): Boolean {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            }
        }
        return true
    }

    private fun canMakeSmores(): Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
    }

    private fun findUnAskedPermissions(wanted: ArrayList<String>): ArrayList<String> {
        val result = ArrayList<String>()

        wanted.filterNotTo(result) { hasPermission(it) }

        return result
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@EnterActivity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {

            ALL_PERMISSIONS_RESULT -> {
                permissionsToRequest.filterNotTo(permissionsRejected) { hasPermission(it) }

                if (permissionsRejected.size > 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected[0])) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(permissionsRejected.toArray(arrayOfNulls(permissionsRejected.size)), ALL_PERMISSIONS_RESULT)
                                        }
                                    })
                            return
                        }
                    }

                }
            }
        }

    }

    fun stopLocationUpdates() {
        if (mGoogleApiClient!!.isConnected) {
            LocationServices.FusedLocationApi
                    .removeLocationUpdates(mGoogleApiClient, this)
            mGoogleApiClient!!.disconnect()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocationChanged(location: Location?) {

        if (location != null) {
            currentLatitude = location.latitude.toString()
            currentLongitude = location.longitude.toString()

            Toast.makeText(this@EnterActivity, currentLatitude + " - " + currentLongitude, Toast.LENGTH_LONG)
        }
    }

    override fun onConnected(p0: Bundle?) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);


        if(mLocation != null)
        {
            currentLatitude = mLocation!!.latitude.toString()
            currentLongitude = mLocation!!.longitude.toString()

            Toast.makeText(this@EnterActivity, currentLatitude + " - " + currentLongitude, Toast.LENGTH_LONG)

            AsyncTaskExample().execute()
        }

        startLocationUpdates()
    }

    override fun onStart() {
        super.onStart()
        if (mGoogleApiClient != null) {
            mGoogleApiClient!!.connect()
        }
    }

    override fun onResume() {
        super.onResume()

        if (!checkPlayServices()) {
            AlertDialog.Builder(this@EnterActivity)
                    .setMessage("Please install Google Play services.")
                    .setPositiveButton("OK", null)
                    .create()
                    .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)

        permissions.add(ACCESS_FINE_LOCATION)
        permissions.add(ACCESS_COARSE_LOCATION)

        permissionsToRequest = findUnAskedPermissions(permissions)
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size > 0)
                requestPermissions(permissionsToRequest.toArray(arrayOfNulls(permissionsToRequest.size)), ALL_PERMISSIONS_RESULT)
        }

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build()

        val items = ArrayList<PageFragment>()
        items.add(PageFragment.newInstance("Page 1", android.R.drawable.ic_dialog_email))
        items.add(PageFragment.newInstance("Page 2", android.R.drawable.ic_dialog_alert))
        items.add(PageFragment.newInstance("Page 3", android.R.drawable.ic_dialog_dialer))

        adapter = PageAdapter(supportFragmentManager)
        adapter.setInitialItems(items)
        //pager = findViewById(R.id.vpPager) as ViewPager
        vpPager.adapter = adapter
        vpPager.offscreenPageLimit = 3

        indicator.setViewPager(vpPager)

        vpPager.setPageTransformer(true, RotateUpTransformer())

        vpPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val isLeftSwipe = previousPosition > position

                if (isLeftSwipe) {
                    //                    Toast.makeText(FourthActivity.this, "left", Toast.LENGTH_SHORT).show();
                } else {
                    //                    Toast.makeText(FourthActivity.this, "right", Toast.LENGTH_SHORT).show();

                    if (adapter.count < 7) {
                        adapter.addItem(PageFragment.newInstance("Page " + (adapter.count + 1).toString(), android.R.drawable.ic_dialog_email))
                    }
                }
                previousPosition = position
                Toast.makeText(this@EnterActivity, (position + 1).toString() + "/" +
                        adapter.count, Toast.LENGTH_SHORT).show()
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        //AsyncTaskExample().execute()

    }

    override fun onBackPressed() {
        if (vpPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            vpPager.currentItem = vpPager.currentItem - 1
        }
    }

    @Suppress("NAME_SHADOWING")
    inner class AsyncTaskExample: AsyncTask<String, DsForecast?, DsForecast?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.visibility = View.VISIBLE
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

                Toast.makeText(this@EnterActivity, result.hourly[0].summary, Toast.LENGTH_LONG)
            }

            progressBar.visibility = View.INVISIBLE

        }

    }
}