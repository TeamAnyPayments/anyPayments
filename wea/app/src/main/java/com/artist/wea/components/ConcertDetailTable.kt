package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

// 콘서트 상세 정보 페이지에서
// 상세 정보를 표시하기 위한 테이블 컴포저블
@Composable
fun ConcertDetailTable(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    dataMap:Map<String, String> = mapOf() // 표현될 데이터들, Map 형태로 전달
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(thickness = 1.dp, color = colorResource(id = R.color.mono200))
        dataMap.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(4.dp, 0.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // 데이터 맵에서 꺼낸 데이터 中 구분자로 UI를 다르게 표시함
                    if(item.key.contains("$")){ // hover info를 표시해야할 경우!
                        Text(
                            text = item.key.replace("$", ""),
                            style = getDefTextStyle()
                        )
                        Icon(
                            Icons.Rounded.Info,
                            contentDescription = "info",
                            modifier = Modifier.size(16.dp),
                            tint = colorResource(id = R.color.mono200)
                        )
                    }else { // 그냥 정보만 표시할 경우
                        Text(
                            text = item.key,
                            style = getDefTextStyle()
                        )
                    }
                }
                // dataMap의 value
                Text(
                    text = item.value,
                    style = getDefTextStyle()
                )
            }
        }
        Divider(thickness = 1.dp, color = colorResource(id = R.color.mono200))
    }

}