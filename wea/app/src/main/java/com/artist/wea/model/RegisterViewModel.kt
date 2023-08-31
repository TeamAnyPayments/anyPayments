package com.artist.wea.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import com.artist.wea.data.UserProfile
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.JSONParser
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

class RegisterViewModel(val repository: RegisterRepository): ViewModel() {

    val jParser:JSONParser = JSONParser() // json 파싱용 util class
    val loginUserRes: MutableLiveData<String> = MutableLiveData() // 로그인 결과 = 토큰 값!
    val getUserInfoRes:MutableLiveData<JSONObject?> = MutableLiveData() // 회원 정보 조회
    val findUserIdRes:MutableLiveData<Boolean> = MutableLiveData() // 사용자 아이디 찾기 결과
    val findUserPasswordRes: MutableLiveData<Any> = MutableLiveData() // 비밀번호 찾기 TODO...
    val logoutRes:MutableLiveData<Any> = MutableLiveData() // 로그아웃 결과
    val joinUserRes: MutableLiveData<Boolean> = MutableLiveData(false) // 회원가입
    val checkUserIdRes = MutableLiveData<Boolean>(); // 아이디 중복
    val sendCodeToEmailRes = MutableLiveData<Boolean>(); // 이메일 전송
    val checkEmailByCodeRes = MutableLiveData<Boolean>(); // 코드 인증 결과

    // 로그인
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


    // 회원정보 조회
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


    // 아이디 찾기
    fun findUserId(name:String, email:String){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.findUserId(name = name, email = email);
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

    // 비밀번호 찾기 TODO...
    fun findUserPassword(name:String, email:String, id:String){
        viewModelScope.launch(exceptionHandler){
            val response = repository.findUserPassword(name = name, email = email, id = id)
            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            findUserPasswordRes.value
        }
    }

    // 로그아웃
    fun logout(){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.logout()

            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonObject = jParser.parseToJson(response)
            logoutRes.value = jsonObject
        }
    }


    // 회원가입
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

    // 아이디 중복 체크
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

    // 이메일 전송
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

    // 코루틴 에러 핸들러 >> coroutine exception Handelr
    protected val exceptionHandler = CoroutineExceptionHandler{ i, exception ->
        Log.d("ERR ::::", "에러 발생.... ${exception.message}");
    }

    companion object{
        val RLOG = "REGISTER_VIEWMODEL :::: "
    }


}