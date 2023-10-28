package com.artist.wea.screen.components

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.artist.wea.R
import com.skydoves.landscapist.glide.GlideImage

/**
   modifier는 imageBox의 modifier이며 width와 height를 지정해주어야한다.
 */
@Composable
fun ImageSelectBox(
    modifier:Modifier,
    contentScale: ContentScale
):Bitmap? {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var (bitmap, _) = remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        imageUri = it
    }

    Column {
        imageUri?.let {
            // 이전에 들고있는 비트맵이 있다면 free
            bitmap?.recycle()
            bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images
                    .Media.getBitmap(context.contentResolver,it)

            } else {
                val source = ImageDecoder
                    .createSource(context.contentResolver,it)
                ImageDecoder.decodeBitmap(source)
            }

            bitmap?.let {  btm ->
                Box(
                    modifier = Modifier.clickable {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                ) {
                    GlideImage(
                        modifier = modifier,
                        imageModel = imageUri,
                        contentScale = contentScale
                    )
                }
            }
        } ?: run {
            Column(
                modifier = modifier
                    .background(colorResource(id = R.color.mono100))
                    .clickable {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "터치해서 이미지 선택")
            }
        }
    }

    return bitmap
}