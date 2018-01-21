package fer.kotlin.weatherapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class TweetModel(private val userName: String, private val userScreenName: String, private val id: String,
                      private val created_ad: String, private val text: String, private val lang: String,
                      private val url: String): Parcelable {
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
