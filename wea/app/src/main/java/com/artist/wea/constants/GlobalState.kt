package com.artist.wea.constants

import androidx.compose.runtime.mutableStateOf
import com.artist.wea.data.ArtistInfo

class GlobalState {
    companion object {
        val currentArtistInfo = mutableStateOf(ArtistInfo())
        var lat:Double = 0.0;
        var lon:Double = 0.0;
    }
}