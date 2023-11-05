package com.artist.wea.data

import java.time.LocalDateTime

data class TicketInfo(
    val serialNo:String,
    val concertName:String,
    val concertImgList:List<String> = listOf(),
    val teamName:String,
    val dateTime: LocalDateTime,
    val location:String,
    val isOnAir:Boolean = false
)
