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
    val checkUserIdRes = MutableLiveData<Any>(); // 아이디 중복
    val sendCodeToEmailRes = MutableLiveData<Any>(); // 이메일 전송
    fun joinUser(joinUser: JoinUser){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.joinUser(joinUser);
            Log.d(RLOG, "RES :: ${response.toString()}")

            if(!response.isSuccessful) throw Exception();
            joinUserRes.value = response.isSuccessful

            val jsonString = response.body()?.string()
            Log.d(RLOG, "${jsonString}")

            val jsonObject = JSONObject(jsonString)
            // val dataArray = jsonObject.getJSONObject("data").getJSONArray("popularChallengeList")

            // joinUserData.value = myState;
            // Log.d("RESPONSE :::: ", "${myList}")
        }
    }

    // 아이디 중복 체크
    fun checkUserId(cui: CheckUserId){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.checkUserId(cui);
            Log.d(RLOG, "RES :: ${response.toString()}")

            if(!response.isSuccessful) throw Exception();

            val jsonString = response.body()?.string()
            Log.d(RLOG, "${jsonString}")

            val jsonObject = JSONObject(jsonString)

            checkUserIdRes.value = jsonObject;

        }
    }

    // 이메일 전송
    fun sendCodeToEmail(emailObj:JSONObject){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.sendCodeToEmail(emailObj)
            Log.d(RLOG, "RES :: ${response.toString()}")

            if(!response.isSuccessful) throw Exception();

            val jsonString = response.body()?.string()
            Log.d(RLOG, "${jsonString}")

            val jsonObject = JSONObject(jsonString)

            sendCodeToEmailRes.value = jsonObject;

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