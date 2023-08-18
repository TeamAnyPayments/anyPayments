package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun TitleInputForm(
    titleText:String = stringResource(id = R.string.empty_text), // 제목 텍스트
    hintText:String = stringResource(id = R.string.text_input_guide), // hintText
    isError:Boolean = false, // error 인가?
    errorText:String = stringResource(id = R.string.empty_text), // 가이드 텍스트
    isPassword:Boolean = false
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
            style = getDefTextStyle()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // inputForm 으로부터 값을 받고 리턴
        inputText.value = InputForm(
            hintText = hintText,
            isError = isError,
            isPassword = isPassword
        )

        // 가이드 텍스트를 제공하는 부분
        if(isError){
            Text(
                text = errorText,
                style = get12TextStyle().copy(
                    color = colorResource(id = R.color.red500),
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )
        }
    }
    return inputText.value
}