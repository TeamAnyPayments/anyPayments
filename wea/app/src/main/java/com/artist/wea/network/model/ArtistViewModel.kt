package com.artist.wea.network.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artist.wea.data.JoinArtist
import com.artist.wea.network.repository.ArtistRepository
import com.artist.wea.util.JSONParser
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ArtistViewModel(val repository: ArtistRepository): ViewModel() {

    val jParser: JSONParser = JSONParser() // json 파싱용 util class

    // TODO... 실제 통신하면서 로직 잡기

    // combined with 아티스트 등록 API
    val joinArtistRes: MutableLiveData<Any> = MutableLiveData() //
    fun joinArtist(joinArtist: JoinArtist) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.joinArtist(joinArtist)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                joinArtistRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            joinArtistRes.value = true

        }
    }

    // combined with 아티스트 조회 API
    val artistInfoRes: MutableLiveData<Any> = MutableLiveData() //
    fun getArtistInfo(name:String) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository. getArtistInfo(name)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                artistInfoRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            artistInfoRes.value = true

        }
    }

    // combined with 아티스트 상세 조회 API
    val artistDetailRes: MutableLiveData<Any> = MutableLiveData() //
    fun getArtistDetail(artistId:Long) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.getArtistDetail(artistId)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                artistDetailRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            artistDetailRes.value = true

        }

    }

    // combined with 아티스트 등록 해제(탈퇴) API
    val quitArtistRes: MutableLiveData<Any> = MutableLiveData() //
    fun quitArtist(deleteMap:Map<String, Long>) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.quitArtist(deleteMap)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                quitArtistRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            quitArtistRes.value = true

        }
    }

    // combined with 아티스트 멤버 초대 API
    val inviteMemberRes: MutableLiveData<Any> = MutableLiveData() //
    suspend fun inviteMember(inviteMap:Map<String, String>) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.inviteMember(inviteMap)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                inviteMemberRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            inviteMemberRes.value = true

        }
    }

    // combined with 아티스트 멤버 초대 요청 수락 API
    val acceptInviteRes: MutableLiveData<Any> = MutableLiveData() //
    fun acceptInvite(acceptMap:Map<String, Long>) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.acceptInvite(acceptMap)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                acceptInviteRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            acceptInviteRes.value = true

        }
    }

    // combined with 아티스트 멤버 초대 요청 거절 API
    val rejectInviteRes: MutableLiveData<Any> = MutableLiveData() //
    fun rejectInvite(refuseMap:Map<String, Long>) {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.rejectInvite(refuseMap)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                rejectInviteRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            rejectInviteRes.value = true

        }

    }

    // combined with 아티스트 프로필 수정 API
    val editArtistInfoRes: MutableLiveData<Any> = MutableLiveData() //
    fun editArtistInfo(modifyArtist: JoinArtist){
        viewModelScope.launch(exceptionHandler) {
            val response = repository.editArtistInfo(modifyArtist)

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                editArtistInfoRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            editArtistInfoRes.value = true
        }
    }

    // combined with 아티스트 프로필 이미지 조회 API
    val getArtistImageRes: MutableLiveData<Any> = MutableLiveData() //
    fun getArtistImage() {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.getArtistImage()

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                getArtistImageRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            getArtistImageRes.value = true
        }
    }

    // combined with 아티스트 프로필 이미지 업로드 API
    val uploadArtistImageRes: MutableLiveData<Any> = MutableLiveData() //
    fun uploadArtistImage() {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.uploadArtistImage()

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                uploadArtistImageRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            uploadArtistImageRes.value = true
        }
    }

    // combined with 아티스트 프로필 이미지 수정 API
    val updateArtistImageRes: MutableLiveData<Any> = MutableLiveData() //
    fun updateArtistImage() {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.updateArtistImage()

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                updateArtistImageRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            updateArtistImageRes.value = true
        }
    }

    // combined with 아티스트 프로필 이미지 삭제 API
    val deleteArtistImageRes: MutableLiveData<Any> = MutableLiveData() //
    fun deleteArtistImage() {
        viewModelScope.launch(exceptionHandler) {
            val response = repository.deleteArtistImage()

            Log.d(ALOG, "${response.toString()}")

            if(!response.isSuccessful) { // 통신 예외 처리
                deleteArtistImageRes.value = null
                throw Exception();
            }

//            val resJsObject = jParser.parseToJson(response)
//            // 예기치 못한 에러 상황에 대응
//            val jsonData = mutableStateOf(JSONObject())
//            if(resJsObject.has("value")){
//                jsonData.value = jParser.parseToJson(value = resJsObject.get("value").toString())
//            }
//            Log.d(ArtistViewModel.ALOG,"${jsonData.value}")

            deleteArtistImageRes.value = true
        }
    }

    // 코루틴 에러 핸들러 >> coroutine exception Handelr
    private val exceptionHandler = CoroutineExceptionHandler{ i, exception ->
        Log.d("ERR ::::", "에러 발생.... $i");
    }

    companion object{
        val ALOG = "ARTIST_VIEWMODEL :::: "
    }
}
