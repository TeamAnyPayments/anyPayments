package com.artist.wea.screen.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.artist.wea.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

// 아이콘, 프로필 등에 최적화된 이미지 표현용 컴포저블
@Composable
fun WeaIconImage(
    modifier:Modifier = Modifier,
    imgUrl:String = "", // 이미지 주소
    bitmap:Bitmap? = null, // 비트맵 객체를 사용할 경우 사용할 비트맵 객체
    size: Dp, // 이미지 사이즈
    isClip:Boolean = false, // 원형으로 집을지 여부
    contentScale:ContentScale = ContentScale.Crop, // 이미지 Scale Type
    defImgID:Int = R.drawable.icon_def_user_img // 기본 이미지 Drawable
){
    val imgModel = bitmap ?: imgUrl.ifEmpty { defImgID }

    // clip 여부에 따른 이미지 modifier 수정
    val iconModifier =
        if(isClip) modifier
            .size(size)
            .clip(shape = RoundedCornerShape(size / 2))
    else modifier
            .size(size)

    GlideImage(
        imageModel = imgModel,
        // contentScale 종류 : Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = contentScale,
        circularReveal = CircularReveal(duration = 100),
        // 이미지 로딩 전 표시할 place holder 이미지
        placeHolder = painterResource(id = defImgID),
        // 에러 발생 시 표시할 이미지
        error = painterResource(id = defImgID),
        modifier = iconModifier
    )
}
