package com.artist.wea.components.sidemenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

// 사이드 메뉴들의 공통된 메뉴 구성을 위해 재활용할 컴포저블
@Composable
fun SideMenuHeader(
    // 메뉴 명 옵션
    korMenuText:String, // 한글 메뉴 명 (필수)
    engMenuText:String = stringResource(id = R.string.empty_text), // 영어 메뉴명 (optional)
    modifier: Modifier = Modifier,
    korTextStyle: TextStyle = getDefTextStyle(), // 메뉴 제목의 한글 텍스트 스타일
    engTextStyle: TextStyle = get14TextStyle(), // 메뉴 제목의 영어 텍스트 스타일
    // 배지 관련 속성
    badgeName:String = stringResource(id = R.string.empty_text), // 배지 명
    badgeColor: Color = colorResource(id = R.color.mono100), // 배지 배경 색상
    badgeTextStyle:TextStyle = get12TextStyle().copy(color = colorResource(id = R.color.white)) // 배지 글자 색상
){

    Row(modifier = modifier
        .wrapContentWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        // 한글 메뉴
        Text(
            text = korMenuText,
            style = korTextStyle
        )
        if(engMenuText.isNotEmpty()){ // 영어 메뉴의 경우 있을 경우만 표시함!
            Text(
                text = engMenuText,
                style = engTextStyle
            )
        }
        // 배지 메뉴의 경우도 있을 경우에만 표시함!
        if(badgeName.isNotEmpty()){
            Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = badgeColor)
            ){
                Text(
                    text = badgeName,
                    style = badgeTextStyle,
                    modifier = Modifier.padding(4.dp, 2.dp)
                )
            }
        }
    }
}