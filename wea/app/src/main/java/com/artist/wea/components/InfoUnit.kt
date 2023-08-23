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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

@Composable
fun InfoUnit(
    modifier: Modifier = Modifier,
    titleText:String = "",
    titleTextStyle:TextStyle = getDefTextStyle().copy(fontSize = 20.sp),
    screen: @Composable () -> Unit = {},
    screenModifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    icon:ImageVector? = null,
    rightMenuIcon:ImageVector? = null,
    rightMenuAction:()-> Unit = {}
){

    // 소개
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                if(icon != null){
                    Icon(icon,
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = titleText,
                    style = titleTextStyle,
                )
            }
            if(rightMenuIcon == null){
                Spacer(modifier = Modifier.size(8.dp))
            }else {
                Icon(
                    rightMenuIcon,
                    contentDescription = "아이콘",
                    modifier = Modifier.size(20.dp).clickable {
                        rightMenuAction()
                    }
                )
            }
        }
        Divider(
            thickness = 1.dp,
            color = colorResource(id = R.color.mono300)
        )
        Column(
            modifier = screenModifier
        ) {
            screen()
        }
    }
}