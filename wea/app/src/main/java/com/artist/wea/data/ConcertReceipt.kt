package com.artist.wea.data

import java.io.Serializable
import java.time.LocalDateTime

// 콘서트 정보 + 토스 페이먼츠 결게 정보를 합쳐 관리할 DTO
data class ConcertReceipt(
    // wea
    val concertId:String = "",
    val minSupportAccount:Int = -1,
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val locations:String = "",
    // toss
    val paymentKey: String = "",
    val orderId: String = "",
    val amount: Number = -1,
): Serializable


