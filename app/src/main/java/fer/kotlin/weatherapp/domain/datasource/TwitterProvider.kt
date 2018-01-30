package fer.kotlin.weatherapp.domain.datasource

import fer.kotlin.weatherapp.data.twitter.TwitterServer
import fer.kotlin.weatherapp.domain.model.TweetModelList

class TwitterProvider(private val twitterServer: TwitterServer = TwitterServer()){

    fun requestTweets(): List<TweetModelList>{
        val tweetMeteoList = twitterServer.requestMeteoTweets()
        val tweetScienceList = twitterServer.requestScienceTweets()
        return listOf(TweetModelList(tweetMeteoList), TweetModelList(tweetScienceList))
    }

}