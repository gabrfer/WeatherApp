package fer.kotlin.weatherapp.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import java.util.ArrayList
import java.util.HashMap


class PermissionUtils {

    private var context: Context
    private var current_activity: Activity

    private var permissionResultCallback: PermissionResultCallback

    private var permission_list = ArrayList<String>()
    private var listPermissionsNeeded = ArrayList<String>()

    private var dialog_content = ""
    private var req_code: Int = 0

    constructor(context: Context) {
        this.context = context
        this.current_activity = context as Activity

        permissionResultCallback = context as PermissionResultCallback

    }

    constructor(context: Context, callback: PermissionResultCallback) {
        this.context = context
        this.current_activity = context as Activity

        permissionResultCallback = callback


    }


    /**
     * Check the API Level & Permission
     *
     * @param permissions
     * @param dialog_content
     * @param request_code
     */

    fun checkPermission(permissions: ArrayList<String>, dialog_content: String, request_code: Int) {
        this.permission_list = permissions
        this.dialog_content = dialog_content
        this.req_code = request_code

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkAndRequestPermissions(permissions, request_code)) {
                permissionResultCallback.permissionGranted(request_code)
                Log.i("all permissions", "granted")
                Log.i("proceed", "to callback")
            }
        } else {
            permissionResultCallback.permissionGranted(request_code)

            Log.i("all permissions", "granted")
            Log.i("proceed", "to callback")
        }

    }


    /**
     * Check and request the Permissions
     *
     * @param permissions
     * @param request_code
     * @return
     */

    private fun checkAndRequestPermissions(permissions: ArrayList<String>, request_code: Int): Boolean {

        if (permissions.size > 0) {
            listPermissionsNeeded = ArrayList()

            for (i in permissions.indices) {
                val hasPermission = ContextCompat.checkSelfPermission(current_activity, permissions[i])

                if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                    listPermissionsNeeded.add(permissions[i])
                }

            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(current_activity, listPermissionsNeeded.toTypedArray(), request_code)
                return false
            }
        }

        return true
    }

    /**
     *
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty()) {
                val perms = HashMap<String, Int>()

                for (i in permissions.indices) {
                    perms.put(permissions[i], grantResults[i])
                }

                val pendingPermissions = ArrayList<String>()

                listPermissionsNeeded.indices
                        .filter { perms[listPermissionsNeeded[it]] != PackageManager.PERMISSION_GRANTED }
                        .forEach {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(current_activity, listPermissionsNeeded[it]))
                                pendingPermissions.add(listPermissionsNeeded[it])
                            else {
                                Log.i("Go to settings", "and enable permissions")
                                permissionResultCallback.neverAskAgain(req_code)
                                Toast.makeText(current_activity, "Go to settings and enable permissions", Toast.LENGTH_LONG).show()
                                return
                            }
                        }

                if (pendingPermissions.size > 0) {
                    showMessageOKCancel(dialog_content,
                            DialogInterface.OnClickListener { _, which ->
                                when (which) {
                                    DialogInterface.BUTTON_POSITIVE -> checkPermission(permission_list, dialog_content, req_code)
                                    DialogInterface.BUTTON_NEGATIVE -> {
                                        Log.i("permisson", "not fully given")
                                        if (permission_list.size == pendingPermissions.size)
                                            permissionResultCallback.permissionDenied(req_code)
                                        else
                                            permissionResultCallback.partialPermissionGranted(req_code, pendingPermissions)
                                    }
                                }
                            })

                } else {
                    Log.i("all", "permissions granted")
                    Log.i("proceed", "to next step")
                    permissionResultCallback.permissionGranted(req_code)

                }


            }
        }
    }


    /**
     * Explain why the app needs permissions
     *
     * @param message
     * @param okListener
     */
    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(current_activity)
                .setMessage(message)
                .setPositiveButton("Ok", okListener)
                .setNegativeButton("Cancel", okListener)
                .create()
                .show()
    }

    interface PermissionResultCallback {
        fun permissionGranted(request_code: Int)
        fun partialPermissionGranted(request_code: Int, granted_permissions: ArrayList<String>)
        fun permissionDenied(request_code: Int)
        fun neverAskAgain(request_code: Int)
    }
}