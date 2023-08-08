package com.artist.wea.data

data class BuskingInfo(
    val buskingTitle:String,
    val buskingImgList:List<String>,
    val buskingIntroduce:String,
    val genre:String = "",
    val startDate: String, // TODO local date...
    val endDate: String, // TODO local date...
    val buskingTime: String, // TODO local time...
    val minSupportAccount:Int = 0,
    val cumulativeAudience:Int = 0,
    val tags:List<String> = listOf(),
    val memberList:List<ArtistInfo> = listOf(),
    val locations:String = "",
)
