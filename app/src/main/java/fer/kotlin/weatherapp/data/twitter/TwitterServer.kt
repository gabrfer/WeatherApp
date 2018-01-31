package fer.kotlin.weatherapp.data.twitter

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import fer.kotlin.weatherapp.domain.model.TweetModel
import java.net.URL

/**
 * Created by Fer on 15/01/2018.
 */
class TwitterServer(val dataMapper: TwitterDataMapper = TwitterDataMapper(), val gson: Gson = GsonBuilder().serializeNulls().create()) {

    companion object {
        private val TYPE_METEO = "meteo"
        private val TYPE_SCIENCE = "science"
        private val URL_USERS = "https://mugan86.com/euskadi_tropikal/twitter/api/v1/user/get.php?type=[TYPE]"
        private val URL_USER_TWEETS = "https://mugan86.com/euskadi_tropikal/twitter/api/v1/tweets/get.php?user=[USER_ID]&items=20"
    }

    private fun requestUsers(usersType: String): TwitterResultUsers{
        val finalURL : String = URL_USERS.replace("[TYPE]", usersType)

        var dsUsersJsonStr = URL(finalURL).readText()
        val indexOfFirstItem = dsUsersJsonStr.indexOf("id", 0, false)
        dsUsersJsonStr = "[{" + dsUsersJsonStr.substring(indexOfFirstItem - 1, dsUsersJsonStr.length - 1)

        val mapper = jacksonObjectMapper()

        val listUsers = mapper.readValue<List<TwitterResultUser>>(dsUsersJsonStr)

        return TwitterResultUsers(listUsers)
    }

    fun requestMeteoTweets(): List<TweetModel>{
        val listServerTweets: MutableList<TweetModel> = mutableListOf()
        val listUsersMeteo = this.requestUsers(fer.kotlin.weatherapp.data.twitter.TwitterServer.TYPE_METEO)

        for (user in listUsersMeteo.listUsers){
            val listUserTweets = gson.fromJson(URL(URL_USER_TWEETS.replace(("[USER_ID]"), user.id)).readText(), TwitterResultUserTweets::class.java)
            listServerTweets.addAll(dataMapper.convertUserTweetsToDomain(user, listUserTweets.tweets))
        }

        return listServerTweets
    }

    fun requestScienceTweets(): List<TweetModel>{
        val listServerTweets: MutableList<TweetModel> = mutableListOf()
        val listUsersScience = this.requestUsers(fer.kotlin.weatherapp.data.twitter.TwitterServer.TYPE_SCIENCE)

        for (user in listUsersScience.listUsers){
            val listUserTweets = gson.fromJson(URL(URL_USER_TWEETS.replace(("[USER_ID]"), user.id)).readText(), TwitterResultUserTweets::class.java)
            listServerTweets.addAll(dataMapper.convertUserTweetsToDomain(user, listUserTweets.tweets))
        }

        return listServerTweets
    }
}