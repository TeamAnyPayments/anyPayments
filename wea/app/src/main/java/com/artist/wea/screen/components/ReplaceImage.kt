package com.artist.wea.screen.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.artist.wea.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReplaceImage(
    modifier: Modifier = Modifier.fillMaxWidth().fillMaxHeight(),
    imageModel:Int = R.drawable.icon_no_image
){
    GlideImage(
        imageModel = imageModel,
        // contentScale 종류 : Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Fit,
        circularReveal = CircularReveal(duration = 100),
        // 이미지 로딩 전 표시할 place holder 이미지
        placeHolder = painterResource(id = R.drawable.icon_loading),
        // 에러 발생 시 표시할 이미지
        error = painterResource(id = R.drawable.icon_no_image),
        modifier = modifier
    )
}