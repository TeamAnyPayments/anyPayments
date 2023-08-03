package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getButtonColor

// 중복체크용 컴포즈 컴포넌트
@Composable
fun DuplicateCheckInputForm(
    titleText:String = stringResource(id = R.string.empty_text),
    hintText:String = stringResource(id = R.string.text_input_guide),
    btnText:String = stringResource(R.string.text_duplicate_check)
):String{

    var inputText = remember { mutableStateOf("") }
    Column(modifier =
    Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        // input 컴포넌트 제목 부분
        Text(
            text = titleText,
            style = TextStyle(
                fontSize = 20.sp,
                // fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.black),
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            // inputForm 으로부터 값을 받고 리턴
            inputText.value = InputForm(
                hintText = hintText,
                modifier = Modifier
                    .width(228.dp)
                    .wrapContentHeight()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                },
                colors = getButtonColor(),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(0.dp),
            ){

                Text(
                    text = btnText,
                    style = get12TextStyle().copy(color = colorResource(id = R.color.white))
                )
            }
        }
    }
    return inputText.value
}



