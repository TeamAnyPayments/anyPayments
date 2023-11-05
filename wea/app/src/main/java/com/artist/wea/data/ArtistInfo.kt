package com.artist.wea.data

import android.graphics.Bitmap

data class ArtistInfo(
    val userId:String = "",
    val profileImgURL:String = "",
    val bgImgURL:String = "",
    val artistName:String = "",
    val location:String = "",
    val comment:String = "",
    val mainIntroduce:String = "",
    val email:String = "",
    // temp
    var profileBitmap:Bitmap? = null,
    var bgBitmap:Bitmap? = null
)
