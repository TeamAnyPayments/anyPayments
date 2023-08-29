package com.artist.wea.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefTextFiledStyle
import com.artist.wea.constants.getDefTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(
    defaultText:String = stringResource(id = R.string.empty_text), // 내부 입력 값
    hintText:String = stringResource(id = R.string.empty_text), // hint 텍스트
    textStyle: TextStyle = getDefTextStyle(), // 텍스트 스타일
    modifier: Modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    isError:Boolean = false,
    errorText:String = stringResource(id = R.string.empty_text), // 가이드 텍스트
    isPassword:Boolean = false,
    isDisable:Boolean = true,
    onTextChange: () -> Unit = {}
):String{
    var text by remember { mutableStateOf(defaultText) }
    Column(
        modifier = modifier
    ){
        TextField(
            value = text,
            onValueChange = {
                onTextChange()
                text = it
            },
            visualTransformation =
            if (isPassword) PasswordVisualTransformation()
            else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = getDefTextFiledStyle(),
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = hintText,
                    style = textStyle.copy(
                        color = colorResource(id = R.color.mono300)
                    )
                )
            },
            isError = text.isNotEmpty() && isError,
            enabled = isDisable
        )

        // 에러인 경우 가이드 텍스트를 표시함
        if(isError&&errorText.isNotEmpty()){
            Text(
                text = errorText,
                style = get12TextStyle().copy(
                    color = colorResource(id = R.color.red500),
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
    }

    return text.toString();
}

