package com.artist.wea.components

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

@Composable
fun WeaIconImage(
    modifier:Modifier = Modifier,
    imgUrl:String = "",
    size: Dp,
    isClip:Boolean = false,
){

    val iconModifier =
        if(isClip) modifier
            .size(size)
            .clip(shape = RoundedCornerShape(size / 2))
    else modifier
            .size(size)

    GlideImage(
        imageModel = imgUrl.ifEmpty { R.drawable.icon_def_user_img },
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 100),
        // shows a placeholder ImageBitmap when loading.
        placeHolder = painterResource(id = R.drawable.default_image),
        // shows an error ImageBitmap when the request failed.
        error = painterResource(id = R.drawable.default_image),
        modifier = iconModifier
    )
}
