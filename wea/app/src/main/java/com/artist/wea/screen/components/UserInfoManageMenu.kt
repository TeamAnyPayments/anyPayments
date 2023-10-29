package com.artist.wea.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle

// 아이디찾기, 회원가입, 비밀번호 찾기 등에 사용할 메뉴 컴포저블
@Composable
fun UserInfoManageMenu(
    navController: NavController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()){
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        ){
        // 아이디 찾기
        Text(
            modifier = Modifier.clickable {
                navController
                    .navigate(PageRoutes.FindId.route)},
            text = stringResource(R.string.text_find_id),
            style = get14TextStyle()
        )
        // 비밀번호 찾기
        Text(
            modifier = Modifier.clickable {
                navController
                    .navigate(PageRoutes.FindPwd.route)},
            text = stringResource(R.string.text_find_pwd),
            style =get14TextStyle()
        )
        // 회원가입
        Text(
            modifier = Modifier.clickable {
                navController
                    .navigate(PageRoutes.UserRegister.route)},
            text = stringResource(R.string.text_regist),
            style = get14TextStyle()
        )
    }
}