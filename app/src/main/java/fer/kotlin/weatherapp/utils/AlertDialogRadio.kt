package fer.kotlin.weatherapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.Toast

interface AlertDialogOkListener {
    fun AlertDialogOK(index: Int, listObjectType: ListObjectType)
}

enum class ListObjectType {
    PROVINCIA,
    MUNICIPIO
}

class AlertDialogRadio(val ctx: Context, val title:String, val list: List<Any>, val type: ListObjectType) {

    var selectedIndex = -1

    fun showAlertDialogRadio() {
        val items: Array<String> = list.map { it.toString() }.toTypedArray()
        val builder = AlertDialog.Builder(ctx)
        builder.setTitle(title)

        builder.setSingleChoiceItems(items, -1, { dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(ctx, items[i], Toast.LENGTH_SHORT).show()
                selectedIndex = i
        })

        builder.setPositiveButton("Aceptar", { dialogInterface: DialogInterface, i: Int ->
                        //Toast.makeText(ctx, "Success", Toast.LENGTH_SHORT).show()

                        val activity = ctx as AlertDialogOkListener
                        activity.AlertDialogOK(selectedIndex, type)

                })

        builder.setNegativeButton("Cancelar",{ dialogInterface: DialogInterface, i: Int ->
                        //Toast.makeText(ctx, "Fail", Toast.LENGTH_SHORT).show()

                })
        val alert = builder.create()
        alert.show()
    }

}