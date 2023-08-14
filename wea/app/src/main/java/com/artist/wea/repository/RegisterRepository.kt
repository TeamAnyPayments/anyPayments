package com.artist.wea.repository

import com.artist.wea.data.CheckUserId
import com.artist.wea.data.JoinUser
import com.artist.wea.instance.Retrofit
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class RegisterRepository {

    // 회원가입
    suspend fun joinUser (joinUser: JoinUser):Response<ResponseBody>{
        return Retrofit.Instance.api.joinUser(joinUser)
    }

    // 중복체크
    suspend fun checkUserId(cui: CheckUserId):Response<ResponseBody>{
        return Retrofit.Instance.api.checkUserId(cui);
    }

    // 이메일 전송
    suspend fun sendCodeToEmail(emailObj: JSONObject):Response<ResponseBody>{
        return Retrofit.Instance.api.sendCodeToEmail(emailObj);
    }
}