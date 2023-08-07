package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.components.uidtclass.AlarmData
import com.artist.wea.constants.get14TextStyle

@Composable
fun AlarmItem(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    alarmData: AlarmData
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(minHeight = 56.dp)
            .background(
                color = alarmData.alarmColor
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Icon(
            alarmData.iconImg,
            contentDescription = "icon",
            modifier = Modifier
                .size(36.dp)
                .padding(start = 8.dp)
                // .weight(1f)
            ,
            tint = alarmData.contentColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = alarmData.content,
                style = get14TextStyle()
                    .copy(
                        color = alarmData.contentColor
                    ),
                modifier = Modifier.weight(if(alarmData.isChek) 3f else 5f)
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                if(alarmData.isChek){
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "checkBtn",
                        modifier = Modifier
                            .size(36.dp),
                        tint = alarmData.checkColor
                    )
                }
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "clearBtn",
                    modifier = Modifier
                        .size(36.dp),
                    tint = alarmData.contentColor
                )
            }
        }

    }
}