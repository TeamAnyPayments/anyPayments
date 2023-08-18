package com.artist.wea.repository

import com.artist.wea.data.CheckUserId
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import com.artist.wea.instance.Retrofit
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class RegisterRepository {

    // 로그인
    suspend fun loginUser(loginUser:LoginUser):Response<ResponseBody>{
        return Retrofit.Instance.user.loginUser(loginUser)
    }

    // 회원가입
    suspend fun joinUser (joinUser: JoinUser):Response<ResponseBody>{
        return Retrofit.Instance.user.joinUser(joinUser)
    }

    // 중복체크
    suspend fun checkUserId(id:String):Response<ResponseBody>{
        return Retrofit.Instance.user.checkUserId(id);
    }

    // 이메일 전송
    suspend fun sendCodeToEmail(email:String):Response<ResponseBody>{
        return Retrofit.Instance.user.sendCodeToEmail(email);
    }

    // 코드 인증
    suspend fun checkEmailByCode(email:String, code:String):Response<ResponseBody>{
        return Retrofit.Instance.user.checkEmailByCode(email=email, code=code)
    }
}