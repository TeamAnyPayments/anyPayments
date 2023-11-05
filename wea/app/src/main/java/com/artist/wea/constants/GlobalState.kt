package com.artist.wea.constants

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import com.artist.wea.data.ArtistInfo
import com.artist.wea.data.UserProfile

class GlobalState {
    companion object {

        // temp
        var isLogin = false; // temp...
        var profileBitmap =  mutableStateOf<Bitmap?>(null);
        var artistProfileBitmap =  mutableStateOf<Bitmap?>(null);
        var artistBackBitmap =  mutableStateOf<Bitmap?>(null);

        var joinedArtistInfo = mutableStateOf<ArtistInfo>(ArtistInfo());


        //
        // 실사용 데이터
        // 현재 로그인 중인 아티스트
        val userProfile = mutableStateOf(UserProfile())

        val currentArtistInfo = mutableStateOf(ArtistInfo())
        var lat:Double = 0.0;
        var lon:Double = 0.0;

        val bookMarkedArtist = mutableMapOf<String, ArtistInfo>(
            // Pair("", ArtistInfo())
        )
        // 아티스트 여부
        val isArtist = mutableStateOf(false)
        val isUser = mutableStateOf(true)
    }
}