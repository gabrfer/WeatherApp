package fer.kotlin.weatherapp.ui.activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.datasource.TwitterProvider
import fer.kotlin.weatherapp.domain.model.TweetModelList
import fer.kotlin.weatherapp.ui.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_twitter.*
import kotlinx.android.synthetic.main.app_bar_main.*

class TwitterActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private lateinit var mSectionsPagerAdapter: ViewPagerAdapter
    private lateinit var tweetsMeteoScience: List<TweetModelList>
    private val twitterProvider: TwitterProvider = TwitterProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        mSectionsPagerAdapter = ViewPagerAdapter(supportFragmentManager, this@TwitterActivity)
        AsyncTaskExample().execute()
    }

    private fun getTweets(){
        this.tweetsMeteoScience = twitterProvider.requestTweets()
    }

    /* Creates the fragments and sets it to ViewPager */
    private fun populateViewPager() {
        mSectionsPagerAdapter.populateViewPagerTwitter(tweetsMeteoScience[0], tweetsMeteoScience[1])
        viewPager.adapter = mSectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    inner class AsyncTaskExample: AsyncTask<String, List<TweetModelList>?, List<TweetModelList>?>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progress_bar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: String?): List<TweetModelList>? {

            var result: List<TweetModelList>? = null

            try {
                result = twitterProvider.requestTweets()
            } catch(Ex: Exception) {
                Log.d("", "Error in doInBackground " + Ex.message)
            }
            return result
        }

        override fun onPostExecute(result: List<TweetModelList>?) {
            super.onPostExecute(result)

            if (result != null) {
                tweetsMeteoScience = result

                if (mSectionsPagerAdapter.count == 0) {
                    populateViewPager()
                }
            }

            progress_bar.visibility = View.GONE
        }
    }
}
