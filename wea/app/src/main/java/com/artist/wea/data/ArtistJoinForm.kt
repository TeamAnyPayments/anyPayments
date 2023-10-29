package com.artist.wea.data

import android.graphics.Bitmap

data class ArtistJoinForm(
    var artistName:String,
    var comment:String,
    var introduce:String,
    var location:String,
    //file
    var artistProfileImage:Bitmap? = null,
    var artistBackBitmap:Bitmap? = null
)
