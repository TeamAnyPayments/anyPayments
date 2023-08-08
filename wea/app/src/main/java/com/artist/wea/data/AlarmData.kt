package com.artist.wea.components.uidtclass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class AlarmData(
    val iconImg: ImageVector, // 아이콘
    val content:String = "", // 알람 내용
    val isChek:Boolean = false, // 초대 수락인지 아닌지
    val alarmColor:Color = Color.LightGray, // 알람 배경
    val contentColor:Color = Color.LightGray, // 글자 컬러
    val checkColor:Color = Color.LightGray // 체크 컬러
)
