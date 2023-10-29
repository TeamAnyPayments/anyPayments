package com.artist.wea.screen.components

import android.app.Activity
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.util.PhotoSelector

@Composable
fun TitleImageInputForm(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    titleText:String,
    isWide:Boolean = false,
    size: Dp = 128.dp
){

    val context = LocalContext.current
    val imageBitmap = remember { mutableStateOf<Bitmap?>(null) } //
    val errorText = stringResource(R.string.text_err_load_img)
    // 사진 불러오기 기능
    val photoSelector = PhotoSelector()
    val takePhotoFromAlbumLauncher = // 갤러리에서 사진 가져오기
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->

                    // Log.d("IMAGE:::", "${uri.toString()}")

                    photoSelector.setImageToVariable(
                        context = context,
                        uri = uri,
                        imageSource = imageBitmap,
                        fileName = "artist_profile"
                    )

                } ?: run {
                    Toast.makeText(context,
                        errorText, Toast.LENGTH_SHORT).show()
                }
            } else if (result.resultCode != Activity.RESULT_CANCELED) {
                // ??
            }
        }

    Column(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // input 컴포넌트 제목 부분
            Text(
                text = titleText,
                style = getDefTextStyle()
            )
        }

        // image Viewer의 역할을 하는 컴포저블
        // 넓게 빼는 이미지이냐 아니냐에 따라 사용할 뷰어를 분기함
        // 정사각형의 이미지가 default
        if(!isWide){
            WeaIconImage(
                size = size,
                bitmap = imageBitmap.value,
                modifier = Modifier.clickable {
                    takePhotoFromAlbumLauncher.launch(photoSelector.takePhotoFromAlbumIntent)
                },
                defImgID = R.drawable.icon_def_user_img
            )
        } else {
            val contentScaleState = remember{ mutableStateOf(ContentScale.Fit) }
            WeaWideImage(
                height = size,
                bitmap = imageBitmap.value,
                modifier = Modifier.clickable {
                    takePhotoFromAlbumLauncher.launch(photoSelector.takePhotoFromAlbumIntent)
                    contentScaleState.value = ContentScale.Crop
                },
                contentScale = contentScaleState.value,
                defImgID = R.drawable.icon_def_user_img
            )
        }
    }

}