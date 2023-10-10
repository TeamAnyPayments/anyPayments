package com.artist.wea.service

import com.artist.wea.data.JoinArtist
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ArtistService {

    // combined with 아티스트 등록 API
    @POST("/artist")
    suspend fun joinArtist(@Body joinArtist: JoinArtist): Response<ResponseBody>

    // combined with 아티스트 조회 API
    @GET("/artist")
    suspend fun getArtistInfo(@Query("name") name:String): Response<ResponseBody>

    // combined with 아티스트 상세 조회 API
    @GET("/artist/detail")
    suspend fun getArtistDetail(@Query("id") artistId:Long): Response<ResponseBody>

    // combined with 아티스트 등록 해제(탈퇴) API
    @DELETE("/artist")
    suspend fun quitArtist(@Body deleteMap:Map<String, Long>): Response<ResponseBody>

    // combined with 아티스트 멤버 초대 API
    @POST("/artist/member")
    suspend fun inviteMember(@Body inviteMap:Map<String, String>): Response<ResponseBody>

    // combined with 아티스트 멤버 초대 요청 수락 API
    @POST("/artist/member/invite")
    suspend fun acceptInvite(@Body acceptMap:Map<String, Long>): Response<ResponseBody>

    // combined with 아티스트 멤버 초대 요청 거절 API
    @DELETE("/artist/member/invite")
    suspend fun rejectInvite(@Body refuseMap:Map<String, Long>): Response<ResponseBody>

    // combined with 아티스트 프로필 수정 API
    @PUT("/artist")
    suspend fun editArtistInfo(@Body modifyArtist: JoinArtist): Response<ResponseBody>

    // combined with 아티스트 프로필 이미지 조회 API
    @GET("/artist/image")
    suspend fun getArtistImage(): Response<ResponseBody>

    // combined with 아티스트 프로필 이미지 업로드 API
    // TODO multipart 적용 필요
    @POST("/artist/image")
    suspend fun uploadArtistImage():Response<ResponseBody>

    // combined with 아티스트 프로필 이미지 수정 API
    // TODO multipart 적용 필요
    @PUT("/artist/image")
    suspend fun updateArtistImage():Response<ResponseBody>

    // combined with 아티스트 프로필 이미지 삭제 API

    @DELETE("/artist/image")
    suspend fun deleteArtistImage():Response<ResponseBody>


}