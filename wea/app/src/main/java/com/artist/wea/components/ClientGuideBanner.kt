package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle

@Composable
fun ClientGuideBanner(
    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = colorResource(id = R.color.mono600))
        .padding(16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Icon(
            Icons.Rounded.Face,
            contentDescription = "",
            modifier = Modifier
                .size(32.dp)
                .weight(1f),
            tint = colorResource(id = R.color.mono100)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(6f)
        ) {
            /*
            서비스를 사용하시면서 불편한 점이 있으셨나요?
             */
            // 페이지 명
            Text(
                text = "서비스를 사용하시면서 불편한 점이 있으셨나요?",
                style = get14TextStyle()
                    .copy(color = colorResource(id = R.color.mono100))
            )
            Text(
                text = "아래의 입력창으로 문의하실 내용을 보내주시면 담당자가 확인 후 빠른 시일 내에 답변 드리도록하겠습니다.",
                style = get12TextStyle()
                    .copy(color = colorResource(id = R.color.mono100))
            )
        }
    }

}