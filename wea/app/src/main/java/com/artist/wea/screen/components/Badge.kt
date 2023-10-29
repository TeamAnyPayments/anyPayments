package com.artist.wea.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle

// 배지 컴포저블
@Composable
fun Badge(
    text:String = "", // 배지 명
    color:Color = colorResource(id = R.color.mono100), // 배지 배경 색상
    txtColor:Color = colorResource(id = R.color.white), // 배지 텍스트 색상
    textStyle: TextStyle = get12TextStyle().copy(color=txtColor), // 뱃지의 텍스트 스타일
){
    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .clip(shape = RoundedCornerShape(4.dp))
        .background(color = color)
        .padding(8.dp)
    ){
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}