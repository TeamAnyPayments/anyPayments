package com.artist.wea.network.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artist.wea.data.ChangePwdData
import com.artist.wea.data.FindIdData
import com.artist.wea.data.FindPwdData
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import com.artist.wea.network.repository.RegisterRepository
import com.artist.wea.util.JSONParser
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class RegisterViewModel(val repository: RegisterRepository): ViewModel() {


    val jParser:JSONParser = JSONParser() // json 파싱용 util class

    // combined with 회원정보 조회 API
    val getUserInfoRes:MutableLiveData<JSONObject?> = MutableLiveData() // 회원 정보 조회 결과
    // 회원 정보 조회 메서드
    fun getUserInfo(){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.getUserInfo()

            Log.d(RLOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                getUserInfoRes.value = null
                throw Exception();
            }
            val resJsObject = jParser.parseToJson(response)
            // 예기치 못한 에러 상황에 대응
            val userInfoJson = mutableStateOf(JSONObject())
            if(resJsObject.has("value")){
                userInfoJson.value = jParser.parseToJson(value = resJsObject.get("value").toString())
            }
            Log.d(RLOG,"${userInfoJson.value}")

            getUserInfoRes.value = userInfoJson.value
        }
    }

    // combined with 회원 가입 API
    val joinUserRes: MutableLiveData<Boolean> = MutableLiveData(false) // 회원가입 결과
    // 회원가입 메서드
    fun joinUser(joinUser: JoinUser){
        viewModelScope.launch(exceptionHandler) {
            // Log.d(RLOG, joinUser.toString())
            val response = repository.joinUser(joinUser);
            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            joinUserRes.value = result
        }
    }

    // combined with 로그인 API
    val loginUserRes: MutableLiveData<String> = MutableLiveData() // 로그인 결과 = 토큰 값!
    // 로그인 메서드
    fun loginUser(loginUser: LoginUser){
        viewModelScope.launch(exceptionHandler){
            val response = repository.loginUser(loginUser)
            Log.d(RLOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = jsonObject.get("value").toString(); // 응답결과로 받은 토큰을 리턴
            loginUserRes.value = result
        }
    }

    // combined with 로그아웃 API
    val logoutRes:MutableLiveData<Boolean> = MutableLiveData() // 로그아웃 결과
    // 로그아웃 메서드
    fun logout(tokenMap : Map<String, String>){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.logout(tokenMap)

            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            logoutRes.value = !jsonObject.get("status").equals("OK")
        }
    }

    // combined with 아이디 찾기 API
    val findUserIdRes:MutableLiveData<Boolean> = MutableLiveData() // 사용자 아이디 찾기 결과
    // 아이디 찾기 메서드
    fun findUserId(findIdData : FindIdData){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.findUserId(findIdData);
            Log.d(RLOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                findUserIdRes.value = true
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK")
            findUserIdRes.value = result
        }
    }

    // combined with 아이디 중복 확인 API
    val checkUserIdRes = MutableLiveData<Boolean>(); // 아이디 중복 확인 결과
    // 아이디 중복 확인 메서드
    fun checkUserId(id:String){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.checkUserId(id);

            if(!response.isSuccessful) { // 통신 예외 처리
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            checkUserIdRes.value =  result
        }
    }

    // combined with 비밀번호 찾기 API
    val findUserPasswordRes: MutableLiveData<Any> = MutableLiveData() // 비밀번호 찾기 결과 TODO...
    // 비밀번호 찾기 메서드 TODO...
    fun findUserPassword(findPwdData : FindPwdData){
        viewModelScope.launch(exceptionHandler){
            val response = repository.findUserPassword(findPwdData)

            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            findUserPasswordRes.value
        }
    }

    // combined with 비밀번호 변경 API
    val changePwdRes = MutableLiveData<Boolean>(); // TODO.. 비밀번호 변경 결과를 보고 반영
    fun changeUserPassword(changePwdData : ChangePwdData) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.changeUserPassword(changePwdData)

            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            changePwdRes.value = result;
        }
    }


    // combined with 이메일 인증 보내기 API
    val sendCodeToEmailRes = MutableLiveData<Boolean>(); // 이메일 인증 보내기 결과
    // 이메일 인증 보내기 메서드
    fun sendCodeToEmail(email:String){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.sendCodeToEmail(email)

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            sendCodeToEmailRes.value = result;

        }
    }

    // combined with 이메일 인증 확인 API
    val checkEmailByCodeRes = MutableLiveData<Boolean>(); // 이메일 인증 확인 결과
    // 코드 인증
    fun checkEmailByCode(email:String, code:String){

        viewModelScope.launch(exceptionHandler) {
            val response = repository.checkEmailByCode(email = email, code = code) // 서버 요청 전송

            if(!response.isSuccessful) { // 통신 예외 처리
                checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            checkEmailByCodeRes.value = result; // 결과 리턴
        }
    }

    // combined with 프로필 이미지 조회 API
    val userImgRes = MutableLiveData<Boolean>(); // 사용자 프로필 이미지 조회 결과
    // 사용자 프로필 이미지 조회 메서드
    fun getUserImg(){
        viewModelScope.launch(exceptionHandler){
            val response = repository.getUserImg()
            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            userImgRes.value = result;
        }
    }

    // combined with 프로필 이미지 업로드 API
    val userUploadImgRes = MutableLiveData<Boolean>(); // 사용자 프로필 이미지 업로드 결과
    // 사용자 프로필 이미지 업로드 메서드
    fun uploadUserImg(
        // multipart...
    ){
        viewModelScope.launch(exceptionHandler){
            val response = repository.uploadUserImg()
            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            userUploadImgRes.value = result;
        }
    }

    // combined with 프로필 이미지 수정 API
    val userEditImgRes = MutableLiveData<Boolean>(); // 사용자 프로필 이미지 수정 결과
    // 사용자 프로필 이미지 수정 메서드
    fun editUserImg(
        // multipart...
    ){
        viewModelScope.launch(exceptionHandler){
            val response = repository.editUserImg()
            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            userEditImgRes.value = result;
        }
    }

    // combined with 프로필 이미지 삭제 API
    val userDeleteImgRes = MutableLiveData<Boolean>(); // 사용자 프로필 이미지 수정 결과
    // 사용자 프로필 이미지 수정 메서드
    fun deleteUserImg(){
        viewModelScope.launch(exceptionHandler){
            val response = repository.deleteUserImg()

            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            userDeleteImgRes.value = result;
        }
    }

    // combined with 사용자 문의 등록 API
    val sendInquiryRes = MutableLiveData<Boolean>() // 사용자 문의 등록 결과
    fun sendInquiry(inquiryMap:Map<String, String>){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.sendInquiry(inquiryMap)

            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }

            val jsonObject = jParser.parseToJson(response)
            val result = !jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
            sendInquiryRes.value = result;
        }
    }


    // 코루틴 에러 핸들러 >> coroutine exception Handelr
    private val exceptionHandler = CoroutineExceptionHandler{ i, exception ->
        Log.d("ERR ::::", "에러 발생.... $i");
    }

    companion object{
        val RLOG = "REGISTER_VIEWMODEL :::: "
    }


}