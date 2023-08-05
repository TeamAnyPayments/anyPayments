package com.artist.wea.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

@Composable
fun PageTopBar(
    navController:NavHostController,
    modifier:Modifier = Modifier,
    pageTitle:String = stringResource(id = R.string.empty_text),
    singleIcon:Painter? = null,
    rightMenuAction:()-> Unit = {}
){
    // TopBar Area
    Column(
        modifier = modifier
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 뒤로 가기
            Icon(
                painter = painterResource(id = R.drawable.icon_angle_left),
                contentDescription = "logo",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            // 페이지 명
            Text(
                text = "우리동네 아티스트, WE:A",
                style = getDefTextStyle()
            )
            // 아이콘
            if(singleIcon == null){
                Spacer(
                    modifier = Modifier.size(24.dp)
                )
            }else {
                Icon(
                    painter = singleIcon,
                    contentDescription = "right-menu",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            rightMenuAction()
                        }
                )
            }
        }
    }
}