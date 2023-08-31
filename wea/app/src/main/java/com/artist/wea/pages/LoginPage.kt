package com.artist.wea.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.components.InputForm
import com.artist.wea.components.LargeButton
import com.artist.wea.components.UserInfoManageMenu
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.LoginUser
import com.artist.wea.instance.Retrofit
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.PreferenceUtil

@Composable
fun LoginPage(
    navController: NavController) {

    // 비동기 통신을 위한 기본 객체 settings
    val context = LocalContext.current; // context
    val mOwner = LocalLifecycleOwner.current
    val repository = RegisterRepository()
    val viewModel = RegisterViewModel(repository)

    val idText = remember { mutableStateOf("") } // 아이디
    val pwdText = remember { mutableStateOf("") } // 비밀번호
    val token = remember { mutableStateOf("") } // 토큰 값

    // 민감 정보 관리를 위한 sharedprefs 객체
    val prefs = PreferenceUtil(context)

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .verticalScroll(scrollState)
        .padding(16.dp, 12.dp)
        .background(color = colorResource(id = R.color.mono50))){

        //title
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.text_login_welcome),
                style = getDefTextStyle().copy(
                    fontSize = 32.sp
                )
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        idText.value = InputForm(hintText = stringResource(R.string.text_id_input_guide))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        pwdText.value = InputForm(
            hintText = stringResource(R.string.text_pwd_input_guide),
            isPassword = true
        )

        // 회원
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        UserInfoManageMenu(navController)
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        
        // 로그인 버튼들
        LargeButton(
            btnText = stringResource(R.string.text_login_btn),
            buttonAction = {
                val loginUser = LoginUser(
                    id = idText.value,
                    password = pwdText.value
                )
                viewModel.loginUser(loginUser);
                // 로그인 결과
                viewModel.loginUserRes.observe(mOwner, Observer {
                    if(it.isNotEmpty()){
                        Log.d("LOGIN RES:::", it.toString())
                        token.value = it.toString()
                        prefs.setString("token", token.value)
                        Retrofit.token.value = it.toString()
                        navController.navigate(PageRoutes.Home.route){
                            popUpTo(0)
                        }

                    }
                })

            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        LargeButton(
            btnText = stringResource(R.string.text_login_btn_naver),
            // nextPage = "register/naver",
            btnIdx = 1, // 네이버
            buttonAction = {

            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

        LargeButton(
            btnText = stringResource(R.string.text_login_btn_kakao),
            btnIdx = 2, // 카카오,
            buttonAction = {

            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))


        Button(onClick = {
            navController.navigate(PageRoutes.Home.route) }) {
            Text("홈페이지 이동")
        }

    }

}