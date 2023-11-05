package com.artist.wea.data

import java.time.LocalDateTime

data class OpenConcertForm(
    // 공연 명
    var concertName:String = "",
    // 공연 컨셉 이미지
    // val concertImage
    // 공연 소개
    var concertIntroduce:String = "",
    // 공연 장르
    var concertGenre:MutableList<String> = mutableListOf(),
    // 공연 시작 시간
    var concertStartTime:LocalDateTime = LocalDateTime.now(),
    // 공연 종료 시간
    var concertEndTime:LocalDateTime = LocalDateTime.now(),
    // 공연 장소
    var concertLocation:String = "",
    // 최소 후원금
    var concertMinPrice:Int = 0,
    // 콘서트 태그
    var concertTags:MutableList<String> = mutableListOf()
)
