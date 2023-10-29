package com.artist.wea.network.service

import com.artist.wea.data.ChangePwdData
import com.artist.wea.data.FindIdData
import com.artist.wea.data.FindPwdData
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query




interface RegisterService {

    // combined with 회원정보 조회 API
    @GET("/user")
    suspend fun getUserInfo():Response<ResponseBody>

    // combined with 회원 가입 API
    @POST("/user")
    suspend fun joinUser(@Body joinUser: JoinUser):Response<ResponseBody>

    // combined with 로그인 API
    @POST("/user/login")
    suspend fun loginUser(@Body loginUser: LoginUser):Response<ResponseBody>

    // combined with 로그아웃 API
    @POST("/user/logout")
    suspend fun logout(@Body tokenMap : Map<String, String>):Response<ResponseBody>

    // combined with 아이디 찾기 API
    @POST("/user/id/find")
    suspend fun findUserId(
        // FindIdData : rest api의 FindIdPostReqDTO와 일치시킴
        @Body findIdData : FindIdData
    ):Response<ResponseBody>


    // combined with 아이디 중복 확인 API
    @POST("/user/id/check")
    suspend fun checkUserId(@Query("id") id:String):Response<ResponseBody>

    // combined with 비밀번호 찾기 API
    @POST("/user/pass/find")
    suspend fun findUserPassword(
        // FindPwdData : rest api의 FindPassPostReqDTO와 일치시킴
        @Body findPwdData : FindPwdData
    ):Response<ResponseBody>

    // combined with 비밀번호 변경 API
    @PUT("/user/pass/change")
    suspend fun changeUserPassword(
        // ChangePwdData : rest api의 ChangePassPutReqDTO와 일치
        @Body changePwdData : ChangePwdData
    ):Response<ResponseBody>

    // combined with 이메일 인증 보내기 API
    @POST("/user/email/send")
    suspend fun sendCodeToEmail(@Query("email") email:String):Response<ResponseBody>

    // combined with 이메일 인증 확인 API
    @POST("/user/email/check")
    suspend fun checkEmailByCode(
        @Query("email") email: String,
        @Query("code") code: String
    ):Response<ResponseBody>


    // combined with 프로필 이미지 조회 API
    @GET("/user/image")
    suspend fun getUserImg():Response<ResponseBody>

    // combined with 프로필 이미지 업로드 API
    @Multipart
    @POST("/user/image")
    suspend fun uploadUserImg( // TODO... 업로드 시, 파라미터의 데이터 타입은 무엇으로..?
        //
    ):Response<ResponseBody>

    // combined with 프로필 이미지 수정 API
    @Multipart
    @PUT("/user/image")
    suspend fun editUserImg( // TODO... 업로드 시, 파라미터의 데이터 타입은 무엇으로..?
        //
    ):Response<ResponseBody>

    // combined with 프로필 이미지 삭제 API
    @DELETE("/user/image")
    suspend fun deleteUserImg():Response<ResponseBody>

    // combined with 사용자 문의 등록 API
    @POST("/user/inquiry")
    suspend fun sendInquiry(
        @Body inquiryMap:Map<String, String>
    ):Response<ResponseBody>


}