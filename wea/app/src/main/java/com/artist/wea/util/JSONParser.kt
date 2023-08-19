package com.artist.wea.util

import android.util.Log
import com.artist.wea.data.UserProfile
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class JSONParser {

    val LOG = "JSON_PARSER ::: "
    // json 객체로 파싱해주는 함수
    fun parseToJson(response: Response<ResponseBody>): JSONObject {
        val jsonString = response.body()?.string()
        val jsonObject = JSONObject(jsonString)
        Log.d(LOG, "${jsonObject.toString()}") // 로그!
        return jsonObject
    }
    fun parseToJson(value:String): JSONObject {
        val jsonObject = JSONObject(value)
        Log.d(LOG, "STR JSON >>> ${jsonObject.toString()}") // 로그!
        return  jsonObject
    }

    fun parseJsonToUserProfile(jObj: JSONObject) : UserProfile{
        val userProfile = UserProfile(
            no = jObj.get("no").toString().toInt(),
            userId = jObj.get("id").toString(),
            name = jObj.get("name").toString(),
            email = jObj.get("email").toString(),
            terms = jObj.get("terms").toString().toBoolean(),
            role = jObj.get("role").toString(),
            socialType = jObj.get("socialType").toString(),
            socialId = jObj.get("socialId").toString(),
        )
        return userProfile
    }
}