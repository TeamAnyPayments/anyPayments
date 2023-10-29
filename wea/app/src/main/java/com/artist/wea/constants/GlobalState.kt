package com.artist.wea.constants

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import com.artist.wea.data.ArtistInfo

class GlobalState {
    companion object {

        // temp
        var isLogin = false; // temp...
        var profileBitmap =  mutableStateOf<Bitmap?>(null);
        var artistProfileBitmap =  mutableStateOf<Bitmap?>(null);
        var artistBackBitmap =  mutableStateOf<Bitmap?>(null);

        ///
        val currentArtistInfo = mutableStateOf(ArtistInfo())
        var lat:Double = 0.0;
        var lon:Double = 0.0;

        val bookMarkedArtist = mutableMapOf<String, ArtistInfo>(
            // Pair("", ArtistInfo())
        )
        val isArtist = mutableStateOf(false)
    }
}