package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefTextStyle

// 중복체크용 컴포즈 컴포넌트
@Composable
fun DuplicateCheckInputForm( // 중복체크, 코드전송에 쓰일 양식용 컴포즈
    titleText:String = stringResource(id = R.string.empty_text), // 제목
    hintText:String = stringResource(id = R.string.text_input_guide), // hint
    btnText:String = stringResource(R.string.text_duplicate_check), // 우측 버튼 텍스트
    btnColor: Color = colorResource(id = R.color.dark_orange300),
    modifier: Modifier = Modifier, // modifier
    isError:Boolean = false, // 에러인가?
    errorText:String = stringResource(id = R.string.empty_text), // 가이드 텍스트
    isDisable:Boolean = true,
    buttonAction: () -> Unit = {}
):String{

    val context = LocalContext.current;

    var inputText = remember { mutableStateOf("") }
    Column(modifier =
    modifier
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
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            // inputForm 으로부터 값을 받고 리턴
            inputText.value = InputForm(
                hintText = hintText,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(3f),
                isError = isError,
                isDisable = isDisable
            )

            // 코드 보내기
            SmallButton(
                btnText = btnText,
                btnColor = btnColor,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .defaultMinSize(minHeight = 40.dp),
                roundSize = 12.dp,
                onClick = {
                      buttonAction()
                },
                textStyle = getDefTextStyle().copy(color = colorResource(id = R.color.white))
            )
        }

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



