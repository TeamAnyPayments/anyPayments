package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle

@Composable
fun GuideBubble(
    guideText:String = stringResource(R.string.text_concert_notify),
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
){
    Box(modifier = modifier
        .padding(4.dp)
        .clip(shape = RoundedCornerShape(8.dp))
        .background(color = colorResource(id = R.color.mono100))
    ){
        Row(modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Icon(
                Icons.Filled.Warning,
                contentDescription = "가이드",
                modifier = Modifier.size(16.dp),
                tint = colorResource(id = R.color.black)
            )
            Text(
                text = guideText,
                style = get12TextStyle()
            )
        }
    }
}