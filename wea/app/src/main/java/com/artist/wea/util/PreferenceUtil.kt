package com.artist.wea.util

import android.content.Context
import android.content.SharedPreferences

// 토큰과 같은 민감 정보를 저장하고 관리허기 위한
// SharePreference 객체 도구
// 별도의 객체로 선언 및 메서드를 정의하여 도구처럼 get, set으로 사용 가능
// 특장점 : 값이 없을 경우 대체값(default)를 설정 할 수 있음
class PreferenceUtil(context: Context) {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context.applicationContext
    }

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences("wea_prefs", Context.MODE_PRIVATE)

    fun getString(key: String, defValue: String?): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

}