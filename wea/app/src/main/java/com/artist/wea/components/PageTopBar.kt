package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

// 각 페이지 별 액션바를 표시할 컴포저블
@Composable
fun PageTopBar(
    navController:NavHostController,
    modifier:Modifier = Modifier,
    pageTitle:String = stringResource(id = R.string.empty_text), // 페이지 명
    singleIcon:ImageVector? = null, // 우측 메뉴 아이콘
    rightMenuText:String = stringResource(id = R.string.empty_text), // 텍스트 메뉴
    rightMenuTextStyle:TextStyle = get14TextStyle(), // 텍스트 메뉴의 스타일
    rightMenuAction:()-> Unit = {}, // 우측 메뉴의 동작 함수
    disableBack:Boolean = false, // 뒤로가기 비활성화 여부
    hasTransparency:Boolean = false, // 투명도 추가 여부
    hasBadge:Boolean = false, // 배지 포함 여부
    badge: @Composable () -> Unit = {}, // 배지 컴포저블
    ){
    // TopBar Area
    Column(
        modifier = modifier
            .background(
                color =
                if (hasTransparency) colorResource(id = R.color.color_transparency)
                else colorResource(id = R.color.mono50),
            )
            .wrapContentHeight()
            .defaultMinSize(minHeight = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(!disableBack)
            {
                // 뒤로 가기
                Icon(
                    Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    tint =
                    if(hasTransparency) colorResource(id = R.color.white)
                    else colorResource(id = R.color.mono900)
                )
            }else {
                Spacer(
                    modifier = Modifier.size(24.dp)
                )
            }
            // 페이지 명
            Text(
                text = pageTitle,
                style = getDefTextStyle()
            )
            // 오른쪽 탑바 메뉴
            if(rightMenuText.isNotEmpty()){ // 텍스트 메뉴인 경우
                Text(
                    text = rightMenuText,
                    style = rightMenuTextStyle,
                    modifier = Modifier.clickable { rightMenuAction() }
                )
            } else if( singleIcon != null){ // 아이콘 메뉴인 경우
                Icon(
                    singleIcon,
                    contentDescription = "right-menu",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            rightMenuAction()
                        }
                )
            }else if(hasBadge){
                badge()
            } else { // 뒤로 가기만 있는 경우
                Spacer(
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}