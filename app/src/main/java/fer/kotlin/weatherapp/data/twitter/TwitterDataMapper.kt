package fer.kotlin.weatherapp.data.twitter

import fer.kotlin.weatherapp.domain.model.TweetModel

/**
 * Created by Fer on 15/01/2018.
 */
class TwitterDataMapper{

    fun convertUserTweetsToDomain(user: TwitterResultUser, listUserTweets: List<TwitterTweets>): List<TweetModel> {
        val listTweetModel: MutableList<TweetModel> = mutableListOf()

        listUserTweets.mapTo(listTweetModel) {
            TweetModel(user.name, user.screen_name, it.id, it.created_ad,
                    it.text, it.lang, it.url)
        }

        return listTweetModel
    }
}