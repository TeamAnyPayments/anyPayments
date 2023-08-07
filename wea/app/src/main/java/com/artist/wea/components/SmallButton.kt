package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get12TextStyle

@Composable
fun SmallButton(
    btnText: String = stringResource(id = R.string.text_def_btn), // 버튼 글자
    navController: NavController?, // 페이지 네비게이션 컨트롤러
    btnColor: Color = colorResource(id = R.color.dark_orange300),
    modifier: Modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight(),
    roundSize: Dp = 64.dp,
    onClick:() -> Unit = {navController?.navigate(PageRoutes.Home.route)},
    textStyle: TextStyle = get12TextStyle().copy(color = colorResource(id = R.color.white))
){
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(roundSize))
            .background(color = btnColor)
            .padding(8.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = btnText,
            style = textStyle
        )
    }
}