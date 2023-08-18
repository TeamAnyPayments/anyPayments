package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle

@Composable
fun Badge(
    text:String = "",
    color:Color = colorResource(id = R.color.mono100),
    txtColor:Color = colorResource(id = R.color.white),
){

    Box(modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight()
        .clip(shape = RoundedCornerShape(4.dp))
        .background(color = color)
        .padding(8.dp)
    ){
        Text(
            text = text,
            style = get12TextStyle()
                .copy(color = txtColor),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}