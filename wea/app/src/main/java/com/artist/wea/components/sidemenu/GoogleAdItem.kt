package com.artist.wea.components.sidemenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle

@Composable
fun GoogleAdItem(
    navController:NavHostController,
    modifier: Modifier = Modifier,
    isMaxHeight:Boolean = false,
    height: Dp = 64.dp
){

    val boxModifier =
        if(!isMaxHeight) modifier
        .height(height)
        else modifier.fillMaxHeight()

    // 구글 광고 올 부분 todo...
    Box(
        modifier =  boxModifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.mono900))
    ) {
        Text(
            text = "광고를 준비 중입니다...",
            style = getDefTextStyle().copy(color = colorResource(id = R.color.white)),
            modifier = Modifier.padding(16.dp).clickable {
                navController.navigate(PageRoutes.Login.route){
                    popUpTo(0)
                }
            }
        )
    }
}