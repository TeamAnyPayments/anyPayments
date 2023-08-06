package com.artist.wea.constants

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R


//토글버튼 기본 세팅
@Composable
fun getToggleButtonColors(
    checkedTrackColor: Color = colorResource(id = R.color.dark_orange300),
    thumbColor:Color = colorResource(id = R.color.white),
    uncheckedTrackColor:Color = colorResource(id = R.color.mono200)
): SwitchColors {
    return SwitchDefaults.colors(
        checkedThumbColor = thumbColor,
        checkedTrackColor = checkedTrackColor,
        // checkedBorderColor = checkedTrackColor,
        // checkedIconColor = checkedIconColor,
        uncheckedThumbColor = thumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        uncheckedBorderColor = uncheckedTrackColor,
        // uncheckedIconColor = colorResource(id = R.color.red300),
    )
}

// card 명암용 속성
@Composable
fun getCardDefElevation(dpSize: Dp = 4.dp): CardElevation {
    return CardDefaults.cardElevation(
        defaultElevation = dpSize,
        pressedElevation = dpSize,
        focusedElevation = dpSize,
        hoveredElevation = dpSize,
        draggedElevation = dpSize,
        disabledElevation = dpSize
    )
}

// 홈 페이지 카드 메뉴 기본 속성
@Composable
fun getCardDefColors():CardColors{
    return CardDefaults.cardColors(
        containerColor = colorResource(id = R.color.mono50),
        contentColor = colorResource(id = R.color.red300),
        disabledContainerColor = colorResource(id = R.color.mono300),
        disabledContentColor = colorResource(id = R.color.mono100)
    )
}

// 소셜 로그인 버튼 용 페인터 가져오는 메서드
@Composable
fun getSocialIcon(idx:Int=0): Painter {
    // 1 : 네이버
    // 2 : 카카오
    when(idx){
        1 -> return painterResource(id = R.drawable.ic_launcher_foreground) // to edit
        2 -> return painterResource(id = R.drawable.ic_launcher_foreground) // to edit
        else -> return painterResource(id = R.drawable.ic_launcher_foreground) // default
    }
}

// 소셜 버튼 index에 따라 버튼 컬러 스타일 가져오는 메서드
@Composable
fun getBtnColorByIdx(idx:Int=0):ButtonColors{
    when(idx){
        1 -> return getNaverBtnColor()
        2 -> return getKaKaoBtnColor()
        else -> return getButtonColor()
    }
}

// 버튼 기본 스타일 속성
@Composable
fun getButtonColor(
    mainColor:Color = colorResource(id = R.color.dark_orange300),
    contentColor: Color = colorResource(id = R.color.white)
):ButtonColors{
    return ButtonDefaults.buttonColors(
        containerColor = mainColor,
        contentColor = contentColor,
        disabledContainerColor = colorResource(id = R.color.mono300),
        disabledContentColor = colorResource(id = R.color.mono100),
    )
}
@Composable
fun getNaverBtnColor():ButtonColors{
    return ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.naver_green),
        contentColor = colorResource(id = R.color.white),
        disabledContainerColor = colorResource(id = R.color.mono300),
        disabledContentColor = colorResource(id = R.color.mono100),
    )
}

@Composable
fun getKaKaoBtnColor():ButtonColors{
    return ButtonDefaults.buttonColors(
        containerColor = colorResource(id = R.color.kakao_yellow),
        contentColor = colorResource(id = R.color.kakao_brown),
        disabledContainerColor = colorResource(id = R.color.mono300),
        disabledContentColor = colorResource(id = R.color.mono100),
    )
}

// 폰트 스타일 속성
@Composable
fun get12TextStyle():TextStyle{
    return getDefTextStyle().copy(fontSize = 12.sp)
}

@Composable
fun get14TextStyle():TextStyle{
    return getDefTextStyle().copy(fontSize = 14.sp);
}

@Composable
fun getDefTextStyle(textColor: Color = colorResource(id = R.color.black)):TextStyle{
    return TextStyle(
        fontSize = 16.sp,
        // fontFamily = FontFamily(Font(R.font.inter)),
        fontWeight = FontWeight(400),
        color = textColor,
    )
}

// 기본 체크박스 스타일
@Composable
fun getDefCheckBoxColor():CheckboxColors{
    return CheckboxDefaults.colors(
        checkedColor = colorResource(id = R.color.dark_orange300),
        uncheckedColor = colorResource(id = R.color.mono300),
        checkmarkColor = colorResource(id = R.color.white)
    )
}

// input text 기본 스타일
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getDefTextFiledStyle():TextFieldColors{
    return  TextFieldDefaults.textFieldColors(
        textColor = colorResource(id = R.color.black),
        containerColor = colorResource(id = R.color.mono50),
        cursorColor = colorResource(id = R.color.black),
        focusedIndicatorColor = colorResource(id = R.color.dark_orange300),
        unfocusedIndicatorColor = colorResource(id = R.color.mono300)
    )
}


