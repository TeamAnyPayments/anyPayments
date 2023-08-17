package com.artist.wea.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artist.wea.data.CheckUserId
import com.artist.wea.data.JoinUser
import com.artist.wea.repository.RegisterRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import org.json.JSONObject

class RegisterViewModel(val repository: RegisterRepository): ViewModel() {

    val joinUserRes: MutableLiveData<Boolean> = MutableLiveData(false) // 회원가입
    val checkUserIdRes = MutableLiveData<Boolean>(false); // 아이디 중복
    val sendCodeToEmailRes = MutableLiveData<Boolean>(false); // 이메일 전송
    val checkEmailByCodeRes = MutableLiveData<Boolean>(false); // 코드 인증 결과


    // 휘원가입
    fun joinUser(joinUser: JoinUser){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.joinUser(joinUser);

            if(!response.isSuccessful) { // 통신 예외 처리
                checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true

            joinUserRes.value = result

        }
    }

    // 아이디 중복 체크
    fun checkUserId(id:String){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.checkUserId(id);

            if(!response.isSuccessful) { // 통신 예외 처리
                checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true

            checkUserIdRes.value =  result
        }
    }

    // 이메일 전송
    fun sendCodeToEmail(email:String){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.sendCodeToEmail(email)

            if(!response.isSuccessful) { // 통신 예외 처리
                checkEmailByCodeRes.value = false;
                throw Exception();
            }
            val jsonString = response.body()?.string()
            val jsonObject = JSONObject(jsonString)
            val result = jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true

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
            val result = jsonObject.get("status").equals("OK"); // 응답결과 OK라면 true
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