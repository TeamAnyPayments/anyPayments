package com.artist.wea.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefTextStyle

/**
 * modifier는 ImageSelectBox의 modifier이며, 이미지의 가로 세로를 정의한다.
 * 기본값은 가로 채우기, 세로 200px이다.
 */
@Composable
fun TitleImageForm(
    titleText:String,
    description:String = "",
    contentScale: ContentScale = ContentScale.None,
    modifier:Modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
):Bitmap?{
    var bitmap:Bitmap? by remember { mutableStateOf(null) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 8.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start
    ) {

        // input 컴포넌트 제목 부분
        Text(
            text = titleText,
            style = getDefTextStyle()
        )
        if (description.isNotEmpty()) {
            Text(
                text = description,
                style = get12TextStyle().copy(
                    color = colorResource(id = R.color.mono300)
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // DropdownTextField 로부터 값을 받고 리턴
        bitmap = ImageSelectBox(
            modifier = modifier,
            contentScale = contentScale
        )
    }
    return bitmap
}