package com.artist.wea.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

// 중복체크용 컴포즈 컴포넌트
@Composable
fun DuplicateCheckInputForm(
    titleText:String = stringResource(id = R.string.empty_text),
    hintText:String = stringResource(id = R.string.text_input_guide),
    btnText:String = stringResource(R.string.text_duplicate_check),
    modifier: Modifier = Modifier,
    navController: NavHostController
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
                    .weight(3f)
            )

            // 코드 보내기
            SmallButton(
                navController = navController,
                btnText = btnText,
                btnColor = colorResource(id = R.color.dark_orange300),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .defaultMinSize(minHeight = 40.dp),
                roundSize = 12.dp,
                onClick = {
                    Toast.makeText(context, "코드가 발송되었습니다.", Toast.LENGTH_SHORT).show()
                },
                textStyle = getDefTextStyle().copy(color = colorResource(id = R.color.white))
            )
        }
    }
    return inputText.value
}



