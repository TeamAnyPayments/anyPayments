package com.artist.wea.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

// 결제 수단의 정보를 표시할 아이템
@Composable
fun PaymentItem(
    name:String = "", // 결제수단 명
    paymentImgURL: Any?, // 결제수단 이미지 링크
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp, 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        WeaIconImage(
            modifier = Modifier.clip(shape = RoundedCornerShape(4.dp)),
            imgUrl = (paymentImgURL?:"").toString(),
            size = 48.dp,
            isClip = false
        )

        // 페이먼츠 명
        Text(
            text = name,
            style = getDefTextStyle()
        )

        // 페이먼츠 삭제 버튼
        Icon(
            Icons.Filled.Close,
            contentDescription = "삭제",
            modifier = Modifier.size(20.dp),
            tint = colorResource(id = R.color.red500)
        )

    }
}