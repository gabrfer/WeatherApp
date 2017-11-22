package fer.kotlin.weatherapp.data.dbHerriak

/**
 * Created by Default on 06/10/2017.
 */
import android.content.Context

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class HerriakOpenHelper(context: Context) : SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_NAME = "herriak.db"
        private val DATABASE_VERSION = 1
    }
}