package com.artist.wea.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.components.InputForm
import com.artist.wea.components.LoginButton
import com.artist.wea.components.UserInfoManageMenu

@Composable
fun LoginPage(
    navController: NavController,
    modifier: Modifier =
                  Modifier
                      .fillMaxSize()) {

    var idText = remember { mutableStateOf("") }
    var pwdText = remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp, 12.dp)){

        //title
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp))
            Text(text = stringResource(R.string.text_login_welcome),
                fontSize = 32.sp,
                style = TextStyle(
                    fontSize = 24.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.black),// to edit
                )
            )
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(32.dp))
        }
        idText.value = InputForm(titleText = stringResource(R.string.text_id_label))
        pwdText.value = InputForm(titleText = stringResource(R.string.text_pwd_label))

        // 회원
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        UserInfoManageMenu()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        
        // 로그인 버튼들
        LoginButton(
            navController=navController,
            buttonText = stringResource(R.string.text_login_btn)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))

        LoginButton(
            navController=navController,
            buttonText = stringResource(R.string.text_login_btn_naver),
            isIcon = true,
            bgColor = colorResource(id = R.color.naver_green),
            textColor = colorResource(id = R.color.white)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))

        LoginButton(
            navController=navController,
            buttonText = stringResource(R.string.text_login_btn_kakao),
            isIcon = true,
            bgColor = colorResource(id = R.color.kakao_yellow),
            textColor = colorResource(id = R.color.kakao_brown)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        Text(text = "${idText.value} | ${pwdText.value}");

    }

}