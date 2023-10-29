package com.artist.wea.screen.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.artist.wea.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

// 배경화면, 넓은 이미지 등에 최적화된 이미지 컴포저블
@Composable
fun WeaWideImage(
    modifier: Modifier = Modifier,
    imgUrl:String = "", // 이미지 주소
    bitmap: Bitmap? = null, // 비트맵 객체를 사용할 경우 사용할 비트맵 객체
    height: Dp, // 이미지 높이 (height)
    contentScale:ContentScale = ContentScale.Crop, // 이미지 Scale Type
    defImgID:Int = R.drawable.default_image // 기본 이미지 Drawable
){

    val imgModel = bitmap ?: imgUrl.ifEmpty { defImgID }

    GlideImage(
        imageModel = imgModel,
        // contentScale 종류 : Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = contentScale,
        circularReveal = CircularReveal(duration = 100), // 이미지 렌더링 시 효과
        // 이미지 로딩 전 표시할 place holder 이미지
        // 이미지 로딩 전 표시할 place holder 이미지
        placeHolder = painterResource(id = R.drawable.icon_loading),
        // 에러 발생 시 표시할 이미지
        error = painterResource(id = R.drawable.icon_no_image),
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    )
}