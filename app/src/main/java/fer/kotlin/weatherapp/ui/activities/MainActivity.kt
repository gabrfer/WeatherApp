package fer.kotlin.weatherapp.ui.activities

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fer.kotlin.weatherapp.R
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.GravityCompat
import fer.kotlin.weatherapp.ui.activities.earthforecast.MainEarthForecastActivity
import fer.kotlin.weatherapp.ui.activities.marsforecast.MarsMainActivity
import fer.kotlin.weatherapp.ui.fragments.tab_general
import fer.kotlin.weatherapp.ui.fragments.tab_rain_wind
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        configureNavigationDrawer()
        configureToolbar()

        imgViewEarth.setOnClickListener {
            // Pass data object in the bundle and populate details activity.
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    imgViewEarth as View,
                    "earthMenu"
            )
            startActivity(intent, options.toBundle())
        }

        //Earth
        linearLayoutEarth.setOnClickListener {
            startActivity<MainEarthForecastActivity>()
        }

        //Mars
        linearLayoutMars.setOnClickListener {
            startActivity<EnterActivity>()
        }

    }

    private fun configureToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar!!.setHomeAsUpIndicator(R.drawable.ic_view_headline)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    private fun configureNavigationDrawer() {

        navigation.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            var f: Fragment? = null
            val itemId = menuItem.itemId

            var a: Activity? = null

            if (itemId == R.id.refresh) {
                f = tab_general()
            } else if (itemId == R.id.stop) {
                f = tab_rain_wind()
            }

            if (f != null) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame, f)
                transaction.commit()
                drawer_layout.closeDrawers()
                return@OnNavigationItemSelectedListener true
            }

            false
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId

        when (itemId) {
        // Android home
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }// manage other entries if you have it ...

        return true
    }

}
