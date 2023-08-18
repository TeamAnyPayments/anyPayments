package com.artist.wea.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artist.wea.data.JoinUser
import com.artist.wea.data.LoginUser
import com.artist.wea.repository.RegisterRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class RegisterViewModel(val repository: RegisterRepository): ViewModel() {

    val loginUserRes: MutableLiveData<String> = MutableLiveData() // 로그인 결과는 토큰 값!
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
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            Log.d(RLOG, "jsonObj = ${jsonObject.toString()}")
            val result = jsonObject.get("value").toString(); // 응답결과로 받은 토큰을 리턴
            loginUserRes.value = result

        }

    }


    // 회원가입
    fun joinUser(joinUser: JoinUser){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.joinUser(joinUser);
            Log.d(RLOG, "res = ${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                // checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            Log.d(RLOG, "jsonObj = ${jsonObject.toString()}")
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
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            Log.d(RLOG, "${jsonObject.toString()}")
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
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            Log.d(RLOG, "${jsonObject.toString()}")
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
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            Log.d(RLOG, "${jsonObject.toString()}")
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