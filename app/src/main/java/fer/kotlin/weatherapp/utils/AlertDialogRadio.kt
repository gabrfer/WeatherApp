package fer.kotlin.weatherapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast

interface AlertDialogOkListener {
    fun alertDialogOK(index: Int, listObjectType: ListObjectType)
}

enum class ListObjectType {
    PROVINCIA,
    MUNICIPIO
}

class AlertDialogRadio(val ctx: Context, val title:String, val list: List<Any>, val type: ListObjectType) {

    private var selectedIndex = -1

    fun showAlertDialogRadio() {
        val items: Array<String> = list.map { it.toString() }.toTypedArray()
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle(title)

        builder.setSingleChoiceItems(items, -1, { _: DialogInterface, i: Int ->
                Toast.makeText(ctx, items[i], Toast.LENGTH_SHORT).show()
                selectedIndex = i
        })

        builder.setPositiveButton("Aceptar", { _: DialogInterface, _: Int ->
                        val activity = ctx as AlertDialogOkListener
                        activity.alertDialogOK(selectedIndex, type)

                })

        builder.setNegativeButton("Cancelar",{ _: DialogInterface, _: Int ->
                })
        val alert = builder.create()
        alert.show()
    }

}