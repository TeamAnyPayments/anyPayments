package com.artist.wea.constants

import androidx.compose.runtime.mutableStateOf
import com.artist.wea.data.ArtistInfo

class GlobalState {
    companion object {
        val currentArtistInfo = mutableStateOf(ArtistInfo())
    }
}