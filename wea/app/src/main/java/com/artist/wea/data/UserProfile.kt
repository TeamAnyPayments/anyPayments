package com.artist.wea.data

data class UserProfile(
    val no:Int = -1,
    val userId:String = "",
    val name:String = "",
    val email:String = "",
    val terms:Boolean = false,
    val role:String = "",
    val socialType:String? = null,
    val socialId:String? = null,
    val profileURL:String = ""
)
