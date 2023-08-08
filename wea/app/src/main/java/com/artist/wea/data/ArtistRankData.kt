package com.artist.wea.components.uidtclass

import androidx.compose.ui.graphics.painter.Painter


data class ArtistRankData(
    val no:Int,
    val artistName:String,
    val artistAddress:String,
    val artistImg: Painter,
)
