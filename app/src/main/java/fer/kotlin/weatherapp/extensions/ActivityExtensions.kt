package fer.kotlin.weatherapp.extensions

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View

fun Activity.showSnackbar(mainTextStringId:Int, actionStringId:Int,
                          listener: View.OnClickListener) {
    Snackbar.make(findViewById(android.R.id.content), getString(mainTextStringId), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(actionStringId), listener).show()
}