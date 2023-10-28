package com.artist.wea.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getCardDefColors
import com.artist.wea.constants.getCardDefElevation
import com.artist.wea.constants.getDefTextStyle

// 홈 페이지의 사이드 메뉴에서 사용할 메뉴 컴포저블
@Composable
fun HomeMenuBox(
    modifier: Modifier = Modifier,
    menuTitle:String = stringResource(id = R.string.empty_text), // 메뉴 이름
    tagName:String = stringResource(id = R.string.empty_text), // 태그 명
    tagTextStyle:TextStyle = getDefTextStyle(colorResource(id = R.color.white)), // 태그 글자 색상
    badgeColor: Color = colorResource(id = R.color.sky_blue300), // 뱃지 색상
    imgPainter:Painter = painterResource(id = R.drawable.ic_launcher_background), // TODO.. 기본 배경으로 바꾸기
    imgSize: Dp = 48.dp, // 아이콘의 크기
    imgPadVer:Dp = 8.dp, // 이미지의 패딩(수직)
    imgPadHor:Dp = 8.dp //  이미지의 패딩(수평)
){

    Card(
        modifier= modifier,
        colors = getCardDefColors(),
        elevation = getCardDefElevation(),
        shape = RoundedCornerShape(16.dp),

    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){

            // 텍스트 메뉴
            Column (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.TopStart)
                .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement =  Arrangement.spacedBy(4.dp)
            ){
                // menu text
                Text(
                    text = menuTitle,
                    style = getDefTextStyle(),
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    textAlign = TextAlign.Start,
                )
                if(tagName.isNotEmpty()){
                    //Spacer(modifier = modifier.height(8.dp))
                    Box(modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(color = badgeColor, shape = CircleShape)
                    ){
                        Text(
                            text = tagName,
                            style = tagTextStyle,
                            modifier = Modifier.padding(8.dp, 4.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomEnd)
                    .padding(vertical = imgPadVer, horizontal = imgPadHor)

            ) {
                Image(
                    painter = imgPainter,
                    contentDescription = "메뉴 이미지",
                    modifier = Modifier
                        .size(imgSize)
                        .align(Alignment.Center)
                )
            }
        }
    }
}