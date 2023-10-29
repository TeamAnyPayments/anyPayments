package com.artist.wea.data

import java.time.LocalDateTime

data class ConcertInfo(
    val concertId:String = "",
    val concertTitle:String,
    val concertImgList:List<String>,
    val concertIntroduce:String,
    val genre:String = "",
    val startDate: LocalDateTime, // TODO local date...
    val endDate: LocalDateTime, // TODO local date...
    val concertTime: String, // TODO local time...
    val minSupportAccount:Int = 0,
    val cumulativeAudience:Int = 0,
    val tags:List<String> = listOf(),
    val memberList:List<ArtistInfo> = listOf(),
    val locations:String = "",
)
