package com.artist.wea.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class LDTParser{
    // Local Date Time 객체를 양식과 함께 입력하면 string으로 변환해주는 파싱용 함수
    fun parseAsStandardString(
        localDateTime:LocalDateTime,
        standard:String,
        localeType:Locale = Locale.KOREA
    ):String{
        return localDateTime.format(DateTimeFormatter.ofPattern(standard, localeType)).toString()
    }
}

