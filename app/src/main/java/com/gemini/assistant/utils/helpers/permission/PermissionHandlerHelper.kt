package com.gemini.assistant.utils.helpers.permission

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat

fun sdkVersionHandler(
    upsideDownCakeCase: (() -> Unit)? = null,
    tiramisuCase: (() -> Unit)? = null,
    olderVersionsCase: (() -> Unit)? = null
) {
    if (isSDKVersionGreaterOrEqual(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)) upsideDownCakeCase?.let { it() }
    else if (isSDKVersionGreaterOrEqual(Build.VERSION_CODES.TIRAMISU)) tiramisuCase?.let { it() }
    else olderVersionsCase?.let { it() }
}

private fun isSDKVersionGreaterOrEqual(sdkVersion: Int) = Build.VERSION.SDK_INT >= sdkVersion

fun Context.goToAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(this)
    }
}

fun isShouldShowRequestPermissionRationale(context: Context, permission: String) = ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED