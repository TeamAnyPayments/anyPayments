package com.artist.wea.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getButtonColor
import com.artist.wea.constants.getDefTextStyle

// 중복체크용 컴포즈 컴포넌트
@Composable
fun DuplicateCheckInputForm( // 중복체크, 코드전송에 쓰일 양식용 컴포즈
    titleText:String = stringResource(id = R.string.empty_text), // 제목
    hintText:String = stringResource(id = R.string.text_input_guide), // hint
    btnText:String = stringResource(R.string.text_duplicate_check), // 우측 버튼 텍스트
    modifier: Modifier = Modifier, // modifier
    isError:Boolean = false, // 에러인가?
    errorText:String = stringResource(id = R.string.empty_text), // 가이드 텍스트
    isDisable:Boolean = true, // 비활성화 여부
    buttonAction: () -> Unit = {}, // 버튼 누를 때 동작할 액션
    isSuccess:Boolean = false, // 중복 체크 확인 여부
    successText:String = stringResource(id = R.string.empty_text), // 성공시 가이드 텍스트
    failText:String = stringResource(id = R.string.empty_text), // 실패시 가이드 텍스트
    btnFlag:Int = 0,
    onTextChange: () -> Unit = {} // 입력값 모니터링 메서드
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
            style = getDefTextStyle()
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
                    .weight(2.5f),
                isError = isError,
                isDisable = isDisable,
                onTextChange = onTextChange
            )
            Button(
                enabled = isDisable,
                onClick = buttonAction,
                colors = getButtonColor(),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(0.dp)
                    .weight(1f)
            ){
                Text(
                    text = btnText,
                    style = getDefTextStyle().copy(
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.white)
                    )
                )
            }
        }

        // 가이드 텍스트를 제공하는 부분
        if(isError){
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
        }else if(isSuccess){
            Text(
                text = successText,
                style = get12TextStyle().copy(
                    color = colorResource(id = R.color.pastel_green300),
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }else if(btnFlag > 0) {
            Text(
                text = failText,
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
    return inputText.value
}



