package com.artist.wea.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R

// CustomDilog를 통한 커스텀 모달창 다이얼로그
@Composable
fun ShowProfileDialog(
    visible: Boolean,
    defaultImageURL:String,
    localImgBitmap: Bitmap?,
    onDismissRequest: () -> Unit,
) {
    if (visible) {
        CustomAlertDialog(onDismissRequest = { onDismissRequest() }) {
            // CUSTOM VIEW...
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = colorResource(id = R.color.mono50)),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    Icons.Filled.Close,
                    contentDescription = "닫기",
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End)
                        .clickable {
                            onDismissRequest()
                        },
                    tint = colorResource(id = R.color.mono600)

                )


                WeaIconImage(
                    imgUrl = defaultImageURL,
                    size = 288.dp,
                    bitmap = localImgBitmap,
                    isClip = false,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
    }
}