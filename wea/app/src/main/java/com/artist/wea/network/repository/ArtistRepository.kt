package com.artist.wea.network.repository

import com.artist.wea.data.JoinArtist
import com.artist.wea.network.instance.Retrofit
import okhttp3.ResponseBody
import retrofit2.Response

class ArtistRepository {

    // combined with 아티스트 등록 API
    suspend fun joinArtist(joinArtist: JoinArtist): Response<ResponseBody> {
        return Retrofit.Instance.artist.joinArtist(joinArtist)
    }

    // combined with 아티스트 조회 API
    suspend fun getArtistInfo(name:String): Response<ResponseBody>{
        return Retrofit.Instance.artist.getArtistInfo(name)
    }

    // combined with 아티스트 상세 조회 API
    suspend fun getArtistDetail(artistId:Long): Response<ResponseBody>{
        return Retrofit.Instance.artist.getArtistDetail(artistId)
    }

    // combined with 아티스트 등록 해제(탈퇴) API
    suspend fun quitArtist(deleteMap:Map<String, Long>): Response<ResponseBody>{
        return Retrofit.Instance.artist.quitArtist(deleteMap)
    }

    // combined with 아티스트 멤버 초대 API
    suspend fun inviteMember(inviteMap:Map<String, String>): Response<ResponseBody>{
        return Retrofit.Instance.artist.inviteMember(inviteMap)
    }

    // combined with 아티스트 멤버 초대 요청 수락 API
    suspend fun acceptInvite(acceptMap:Map<String, Long>): Response<ResponseBody>{
        return Retrofit.Instance.artist.acceptInvite(acceptMap)
    }

    // combined with 아티스트 멤버 초대 요청 거절 API
    suspend fun rejectInvite(refuseMap:Map<String, Long>): Response<ResponseBody>{
        return Retrofit.Instance.artist.rejectInvite(refuseMap);
    }

    // combined with 아티스트 프로필 수정 API
    suspend fun editArtistInfo(modifyArtist: JoinArtist): Response<ResponseBody>{
        return Retrofit.Instance.artist.editArtistInfo(modifyArtist)
    }

    // combined with 아티스트 프로필 이미지 조회 API
    suspend fun getArtistImage(): Response<ResponseBody>{
        return Retrofit.Instance.artist.getArtistImage()
    }

    // combined with 아티스트 프로필 이미지 업로드 API
    suspend fun uploadArtistImage(): Response<ResponseBody>{
        return Retrofit.Instance.artist.uploadArtistImage()
    }

    // combined with 아티스트 프로필 이미지 수정 API
    suspend fun updateArtistImage(): Response<ResponseBody>{
        return Retrofit.Instance.artist.updateArtistImage()
    }

    // combined with 아티스트 프로필 이미지 삭제 API
    suspend fun deleteArtistImage(): Response<ResponseBody>{
        return Retrofit.Instance.artist.deleteArtistImage()
    }

}