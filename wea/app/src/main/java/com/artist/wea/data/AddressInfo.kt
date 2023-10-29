package com.artist.wea.data

data class AddressInfo(
    // 우편번호
    val postalCode: String = "",
    // 주소
    val address: String = "",
    // 상세주소
    var addressDetail: String = ""
)
