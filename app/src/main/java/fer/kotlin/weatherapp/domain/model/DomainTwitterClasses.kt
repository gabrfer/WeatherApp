package fer.kotlin.weatherapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class TweetModelList(public val listTweets: List<TweetModel>): Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(TweetModel)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(listTweets)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TweetModelList> {
        override fun createFromParcel(parcel: Parcel): TweetModelList {
            return TweetModelList(parcel)
        }

        override fun newArray(size: Int): Array<TweetModelList?> {
            return arrayOfNulls(size)
        }
    }
}

data class TweetModel(val userName: String, val userScreenName: String, val id: String,
                      val created_ad: String, val text: String, val lang: String,
                      val url: String): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(userScreenName)
        parcel.writeString(id)
        parcel.writeString(created_ad)
        parcel.writeString(text)
        parcel.writeString(lang)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<TweetModel> {
        override fun createFromParcel(parcel: Parcel): TweetModel {
            return TweetModel(parcel)
        }

        override fun newArray(size: Int): Array<TweetModel?> {
            return arrayOfNulls(size)
        }
    }

}
