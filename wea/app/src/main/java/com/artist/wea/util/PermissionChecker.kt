package com.artist.wea.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class PermissionChecker(private val activity: Activity, private val context: Context) {

    val PERMISSION_STATE_CODE = 100 // 권한 요청 코드
    val permissionList = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // 권한 요청 함수
    fun requestPermission():Boolean {
        if (checkAllPermissions()) {
            // 모든 권한이 허용되어 있음
            // 위치 정보를 얻는 작업 수행
            return true;
        } else {
            // 하나 이상의 권한이 허용되지 않음
            ActivityCompat.requestPermissions(activity, permissionList, PERMISSION_STATE_CODE)
            return false;
        }
    }

    // 사용자에게 요청해야할 권한 허용
    fun checkAllPermissions(): Boolean {
        for (permission in permissionList) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    // 사용자가 2회 이상 권한 허용해주지 않으면 앱 권한을 띄워서 허용하도록 함.
    fun openAppSettings(context: Context) {
        ToastManager.shortToast(context, "허용되지 않은 권한이 있습니다.")
        val appSettingsIntent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        appSettingsIntent.data = uri
        context.startActivity(appSettingsIntent)
    }

}