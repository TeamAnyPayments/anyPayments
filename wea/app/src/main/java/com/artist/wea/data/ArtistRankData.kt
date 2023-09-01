package com.artist.wea.components.data

import com.artist.wea.R


data class ArtistRankData(
    val no:Int,
    val artistName:String,
    val artistAddress:String,
    val artistImgURL: String = R.drawable.icon_def_user_img.toString(),
)
