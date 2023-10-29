package com.artist.wea.data

// ConcertListInfo
data class ConcertListInfo(
    val concertId:String = "",
    val imgURL: String = "",
    var artistName: String = "-",
    val concertIntroduce:String = "-",
    val location:String = "-",
    var latitude:Double = 0.0,
    var longitude:Double = 0.0,
    val email:String = "-",
    val userId:String = "-"
)
