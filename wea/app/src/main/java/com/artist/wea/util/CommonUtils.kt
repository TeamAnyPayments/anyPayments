package com.artist.wea.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.artist.wea.constants.GlobalState
import com.artist.wea.network.instance.Retrofit
import com.google.android.gms.location.FusedLocationProviderClient
import java.io.Serializable
import java.util.Random


// 앱 내에서 자주 사용하는 다양한 도구성 메서드를 구현한 클래스
class CommonUtils {

    companion object {
        // serializable
        fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
        {
            return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                activity.intent.getSerializableExtra(name, clazz)!!
            else
                @Suppress("DEPRECATION")
                activity.intent.getSerializableExtra(name) as T
        }

        val LOCATION_PERMISSION_REQUEST_CODE =  1001;

        // 위치 권한 확인 및 요청
        fun checkLocationPermission(context: Context){
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            }
        }

        // 위치 업데이트 요청
        fun requestLocationUpdates(context: Context, fusedLocationClient: FusedLocationProviderClient){
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                // 권한이 허용된 경우
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    // 위치 업데이트 수신 및 사용
                    val latitude = location?.latitude
                    val longitude = location?.longitude

                    GlobalState.lat = latitude ?: 0.0
                    GlobalState.lon = longitude ?: 0.0
                    Log.d("LOCATION_TOOOLS", "현재 위치 정보 ${latitude}, ${longitude}")

                }
            }
        }
        // 난수 생성
        val random = Random()
        fun getRandomValue(from: Int, to: Int) : Int {
            return random.nextInt(to - from) + from
        }

        // 로그인 여부 체크 함수
        fun checkLoginInfo(context: Context):Boolean{

            // 임시 치트키
            if(GlobalState.isLogin) return true;

            // 로그인 시 로그인 페이지로 이동하지 않도록, 자동로그인을 위해 로그인 여부를 체크함
            val context = context;
            val prefs = PreferenceUtil(context);
            val token = prefs.getString("token", "");
            if(token.isNotEmpty()){ // temp... : 토큰정보 확인용
                Log.d("MAIN_ACTIVITY:::", "토큰 정보 있음")
                Retrofit.token.value = token
                return true; // 로그인 성공시 true 리턴
            }
            return false; // 토큰 정보 없을 경우 false 리턴
        }
    }

    //data class to store the user Latitude and longitude
    data class LatandLong(
        var latitude: Double = 0.0,
        var longitude: Double = 0.0
    )
}