package com.artist.wea.components.uidtclass

import com.artist.wea.R


data class ArtistRankData(
    val no:Int,
    val artistName:String,
    val artistAddress:String,
    val artistImgURL: String = R.drawable.icon_def_user_img.toString(),
)
