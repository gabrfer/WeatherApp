package fer.kotlin.weatherapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.TweetModel
import fer.kotlin.weatherapp.domain.model.TweetModelList
import fer.kotlin.weatherapp.extensions.ctx
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetListAdapter(private val tweetModelList: TweetModelList): RecyclerView.Adapter<TweetListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_tweet, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(tweetModelList.listTweets[position])
    }

    override fun getItemCount(): Int = tweetModelList.listTweets.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindForecast(tweet: TweetModel) {
            with(tweet) {

                itemView.txtDisplayName.text = tweet.userScreenName
                itemView.txtUsername.text = tweet.userName
                itemView.txtTweet.text = tweet.text
                itemView.txtTweetDatetime.text = tweet.created_ad
                itemView.txtTweetUrl.text = tweet.url

            }
        }
    }

}