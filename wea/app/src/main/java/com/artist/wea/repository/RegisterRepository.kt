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

    // 회원정보 조회
    suspend fun getUserInfo():Response<ResponseBody>{
        return Retrofit.Instance.user.getUserInfo()
    }

    // 아이디 찾기
    suspend fun findUserId(name:String, email:String):Response<ResponseBody>{
        return Retrofit.Instance.user.findUserId(name = name, email = email)
    }

    // 비밀번호 찾기
    suspend fun findUserPassword(name:String, email:String, id:String):Response<ResponseBody>{
        return Retrofit.Instance.user.findUserPassword(name = name, email = email, id = id)
    }

    // 로그아웃
    suspend fun logout():Response<ResponseBody>{
        return Retrofit.Instance.user.logout()
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