package com.artist.wea.service

import com.artist.wea.data.CheckUserId
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface RegisterService {

    // 로그인
    @POST("/user/login")
    suspend fun loginUser(@Body loginUser: LoginUser): Response<ResponseBody>

    // 회원 가입
    @POST("/user")
    suspend fun joinUser(@Body joinUser: JoinUser): Response<ResponseBody>

    // 아이디 중복 체크
    @POST("/user/id/check")
    suspend fun checkUserId(@Query("id") id:String): Response<ResponseBody>
    // 이메일 전송
    @POST("/user/email/send")
    suspend fun sendCodeToEmail(@Query("email") email:String):Response<ResponseBody>

    // 코드 인증
    @POST("/user/email/check")
    suspend fun checkEmailByCode(@Query("email") email:String, @Query("code") code:String):Response<ResponseBody>

}