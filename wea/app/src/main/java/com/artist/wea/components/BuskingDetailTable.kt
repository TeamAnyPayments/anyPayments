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

@Composable
fun BuskingDetailTable(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    dataMap:Map<String, String> = mapOf()
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
                    if(item.key.contains("$")){
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
                    }else {
                        Text(
                            text = item.key,
                            style = getDefTextStyle()
                        )
                    }
                }
                Text(
                    text = item.value,
                    style = getDefTextStyle()
                )
            }
        }
        Divider(thickness = 1.dp, color = colorResource(id = R.color.mono200))
    }

}