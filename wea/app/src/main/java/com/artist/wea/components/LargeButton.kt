package com.artist.wea.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getBtnColorByIdx
import com.artist.wea.constants.getSocialIcon

@Composable
fun LargeButton(
    btnText: String = stringResource(id = R.string.text_def_btn), // 버튼 글자
    btnIdx:Int = 0, // 버튼 종류 index, 소셜인지 일반인지 구분용
    hasIcon:Boolean = btnIdx != 0, // 아이콘이 필요한 버튼인지 for 소셜 로그인,
    btnColors: ButtonColors = getBtnColorByIdx(btnIdx),
    imgPainter:Painter = getSocialIcon(btnIdx),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp),
    buttonAction:() -> Unit
    ) {


    Button(
        onClick = {
            buttonAction()
        },
        colors = btnColors,
        modifier = modifier,
    ) {
        Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
            ) {

            if(hasIcon){
                // Painter
                Image(
                    painter = imgPainter,
                    contentDescription = stringResource(R.string.text_social_img_desc),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(32.dp)
                        .height(32.dp)
                )
            }
            Text(
                text = btnText,
                // style = getDefTextStyle().copy(color = colorResource(id = R.color.white), textAlign = TextAlign.Center),
                modifier = if(!hasIcon) Modifier.fillMaxWidth().padding(6.dp) else Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center
            )
            if(hasIcon){
                Spacer( modifier = Modifier
                    .width(32.dp)
                    .height(32.dp))
            }
        }
    }

}
