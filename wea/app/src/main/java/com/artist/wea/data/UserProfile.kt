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
    val profileURL:String = "https://mblogthumb-phinf.pstatic.net/MjAyMDAyMDdfMTYw/MDAxNTgxMDg1NzUxMTUy.eV1iEw2gk2wt_YqPWe5F7SroOCkXJy2KFwmTDNzM0GQg.Z3Kd5MrDh07j86Vlb2OhAtcw0oVmGCMXtTDjoHyem9og.JPEG.7wayjeju/%EB%B0%B0%EC%9A%B0%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84_IMG7117.jpg?type=w800"
)
