package com.artist.wea.components

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

@Composable
fun WeaWideImage(
    modifier: Modifier = Modifier,
    imgUrl:String = "",
    bitmap: Bitmap? = null,
    height: Dp,
){

    val imgModel = bitmap ?: imgUrl.ifEmpty { R.drawable.icon_def_user_img }

    GlideImage(
        imageModel = imgModel,
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 100),
        // shows a placeholder ImageBitmap when loading.
        placeHolder = painterResource(id = R.drawable.default_image),
        // shows an error ImageBitmap when the request failed.
        error = painterResource(id = R.drawable.default_image),
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    )
}