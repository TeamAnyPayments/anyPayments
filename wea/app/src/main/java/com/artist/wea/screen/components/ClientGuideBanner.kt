package com.artist.wea.screen.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle

// CS 메뉴 에서 상단 배너 부분을 표시할 컴포저블
@Composable
fun ClientGuideBanner(
    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = colorResource(id = R.color.mono600))
        .padding(16.dp),
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
            // 안내 사항들...
            Text(
                text = stringResource(R.string.text_cs_guide_header),
                style = get14TextStyle()
                    .copy(color = colorResource(id = R.color.mono100))
            )
            Text(
                text = stringResource(R.string.text_cs_guide_comment),
                style = get12TextStyle()
                    .copy(color = colorResource(id = R.color.mono100))
            )
        }
    }

}