package com.artist.wea.service

import com.artist.wea.data.CheckUserId
import com.artist.wea.data.JoinUser
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    // 회원 가입
    @POST("/user")
    suspend fun joinUser(@Body joinUser: JoinUser): Response<ResponseBody>

    // 아이디 중복 체크
    @POST("/user/id/duplicate")
    suspend fun checkUserId(@Body cui: CheckUserId): Response<ResponseBody>

    // 이메일 전송
    @POST("/user/email/send")
    suspend fun sendCodeToEmail(@Body emailObj:JSONObject):Response<ResponseBody>

}