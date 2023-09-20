package com.artist.wea.repository

import com.artist.wea.data.ChangePwdData
import com.artist.wea.data.FindIdData
import com.artist.wea.data.FindPwdData
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import com.artist.wea.instance.Retrofit
import okhttp3.ResponseBody
import retrofit2.Response

class RegisterRepository {

    // combined with 회원정보 조회 API
    suspend fun getUserInfo():Response<ResponseBody>{
        return Retrofit.Instance.user.getUserInfo()
    }

    // combined with 회원 가입 API
    suspend fun joinUser (joinUser: JoinUser):Response<ResponseBody>{
        return Retrofit.Instance.user.joinUser(joinUser)
    }

    // combined with 로그인 API
    suspend fun loginUser(loginUser:LoginUser):Response<ResponseBody>{
        return Retrofit.Instance.user.loginUser(loginUser)
    }

    // combined with 로그아웃 API
    suspend fun logout(tokenMap : Map<String, String>):Response<ResponseBody>{
        return Retrofit.Instance.user.logout(tokenMap)
    }

    // combined with 아이디 찾기 API
    suspend fun findUserId(findIdData : FindIdData):Response<ResponseBody>{
        return Retrofit.Instance.user.findUserId(findIdData)
    }

    // combined with 아이디 중복 확인 API
    suspend fun checkUserId(id:String):Response<ResponseBody>{
        return Retrofit.Instance.user.checkUserId(id);
    }

    // combined with 비밀번호 찾기 API
    suspend fun findUserPassword(findPwdData : FindPwdData):Response<ResponseBody>{
        return Retrofit.Instance.user.findUserPassword(findPwdData)
    }

    // combined with 비밀번호 변경 API
    suspend fun changeUserPassword(changePwdData : ChangePwdData):Response<ResponseBody>{
        return Retrofit.Instance.user.changeUserPassword(changePwdData)
    }

    // combined with 이메일 인증 보내기 API
    suspend fun sendCodeToEmail(email:String):Response<ResponseBody>{
        return Retrofit.Instance.user.sendCodeToEmail(email);
    }

    // combined with 이메일 인증 확인 API
    suspend fun checkEmailByCode(email:String, code:String):Response<ResponseBody>{
        return Retrofit.Instance.user.checkEmailByCode(email=email, code=code)
    }

    // combined with 프로필 이미지 조회 API
    suspend fun getUserImg():Response<ResponseBody>{
        return Retrofit.Instance.user.getUserImg()
    }

    // combined with 프로필 이미지 업로드 API
    // TODO... 업로드 시, 파라미터의 데이터 타입은 무엇으로..?
    suspend fun uploadUserImg():Response<ResponseBody>{
        return Retrofit.Instance.user.uploadUserImg()
    }

    // combined with 프로필 이미지 수정 API
    // TODO... 업로드 시, 파라미터의 데이터 타입은 무엇으로..?
    suspend fun editUserImg():Response<ResponseBody>{
        return Retrofit.Instance.user.editUserImg()
    }

    // combined with 프로필 이미지 삭제 API
    suspend fun deleteUserImg():Response<ResponseBody>{
        return Retrofit.Instance.user.deleteUserImg()
    }

    // combined with 사용자 문의 등록 API
    suspend fun sendInquiry(inquiryMap:Map<String, String>):Response<ResponseBody>{
        return Retrofit.Instance.user.sendInquiry(inquiryMap)
    }


}