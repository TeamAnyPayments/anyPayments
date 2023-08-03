package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefCheckBoxColor


// 약관 동의 체크박스 전체 컴포즈 컴포넌트
@Composable
fun CheckBoxScreen(
    navController: NavController,
) {
    // 각 항목별 레이아웃
    val (checked1, setChecked1) = remember { mutableStateOf(false) }
    val (checked2, setChecked2) = remember { mutableStateOf(false) }
    // var (checked3, setChecked3) = mutableStateOf<Boolean>(false);
    val allChecked = checked1 && checked2 // 전체 동의 여부
            //&& checked3;

    Column(verticalArrangement = Arrangement.Center) {
        CheckBoxRow(
            text = stringResource(id = R.string.text_all_check),
            value = allChecked,
            onClick = {
                if (allChecked) {
                    setChecked1(false)
                    setChecked2(false)
                    // setChecked3(false)
                } else {
                    setChecked1(true)
                    setChecked2(true)
                    // setChecked3(true)
                }
            },
        )
        CheckBoxRow(
            text = stringResource(R.string.text_term),
            value = checked1,
            onClick = { setChecked1(!checked1) },
            hasMore = true)
        CheckBoxRow(
            text = stringResource(R.string.text_policy),
            value = checked2,
            onClick = { setChecked2(!checked2) },
            hasMore = true)
    }
}

// 약관 동의 체크박스 컴포즈 컴포넌트
@Composable
fun CheckBoxRow(
    text: String, value: Boolean, onClick: (Any) -> Unit,
    hasMore:Boolean = false,
) {
    val defTextStyle = get14TextStyle()
        .copy(color = colorResource(id = R.color.mono800))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(
            checked = value, 
            onCheckedChange = onClick,
            colors = getDefCheckBoxColor()
        )
        Spacer(modifier = Modifier.width(8.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            ClickableText(
                text = AnnotatedString(text),
                onClick = onClick,
                modifier =
                if(!hasMore) Modifier.fillMaxWidth()
                else Modifier.wrapContentWidth()
            )
            Text(
                stringResource(R.string.text_see_more),
                style = defTextStyle
            )
        }
    }
}