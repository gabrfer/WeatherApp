package fer.kotlin.weatherapp.data.twitter

data class TwitterResultUsers(val listUsers: List<TwitterResultUser>)

data class TwitterResultUser(val id: String, val name: String, val screenName: String, val description: String,
                             val followers_count: String, val friends_count: String, val permanent: String, val lang: String,
                             val type: String, val profile_img: String)

data class TwitterResultUserTweets(val id: String, val screenName: String, val followers_count: String,
                                   val friends_count: String, val permanent: String, val lang: String,
                                   val basicInfo: TwitterTweetsBasicInfo, val tweets: List<TwitterTweets>)

data class TwitterTweetsBasicInfo(val current_page: Int, val pages: Int)

data class TwitterTweets(val id: String, val created_ad: String, val text: String, val lang: String, val url: String)