package com.artist.wea.components

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

@Composable
fun WeaIconImage(
    modifier:Modifier = Modifier,
    imgUrl:String = "",
    bitmap:Bitmap? = null,
    size: Dp,
    isClip:Boolean = false,
    contentScale:ContentScale = ContentScale.Crop,
    defImgID:Int = R.drawable.icon_def_user_img
){
    val imgModel = bitmap ?: imgUrl.ifEmpty { defImgID }

    val iconModifier =
        if(isClip) modifier
            .size(size)
            .clip(shape = RoundedCornerShape(size / 2))
    else modifier
            .size(size)

    GlideImage(
        imageModel = imgModel,
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = contentScale,
        circularReveal = CircularReveal(duration = 100),
        // shows a placeholder ImageBitmap when loading.
        placeHolder = painterResource(id = defImgID),
        // shows an error ImageBitmap when the request failed.
        error = painterResource(id = defImgID),
        modifier = iconModifier
    )
}
