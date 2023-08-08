package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ArtistInfoUnit(
    modifier: Modifier = Modifier,
    titleText:String = "",
    screen: @Composable () -> Unit,
    screenModifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
){

    // 프로필 풀 소개
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // header
        Text(
            text = titleText,
            style = getDefTextStyle().copy(fontSize = 20.sp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
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