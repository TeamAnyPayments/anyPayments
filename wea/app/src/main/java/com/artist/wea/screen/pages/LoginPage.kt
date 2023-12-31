package com.artist.wea.screen.pages

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
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.LoginUser
import com.artist.wea.network.instance.Retrofit
import com.artist.wea.network.model.RegisterViewModel
import com.artist.wea.network.repository.RegisterRepository
import com.artist.wea.screen.components.InputForm
import com.artist.wea.screen.components.LargeButton
import com.artist.wea.screen.components.UserInfoManageMenu
import com.artist.wea.util.PreferenceUtil
import com.artist.wea.util.ToastManager.Companion.shortToast

// 로그인 페이지
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

        // 로그인 결과에 따른 토스트 메세지용 문자열
        val loginSuccess = stringResource(R.string.text_login_success)
        val loginFail = stringResource(R.string.text_login_fail)
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
                        // 로그인 결과 파싱
                        token.value = it.toString()
                        prefs.setString("token", token.value) // 다른 화면에서 활용할 수 있도록 토큰 정보를 저장
                        Retrofit.token.value = it.toString() // 로그인 성공 시 레트로핏 인스턴스 헤더에 토큰을 붙여준다.
                        shortToast(context, loginSuccess)
                        navController.navigate(PageRoutes.Home.route){
                            popUpTo(0)
                        }
                    }else {
                        shortToast(context, loginFail)
                    }
                })

            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))

//        LargeButton(
//            btnText = stringResource(R.string.text_login_btn_naver),
//            // nextPage = "register/naver",
//            btnIdx = 1, // 네이버
//            buttonAction = {
//
//            }
//        )
//        Spacer(modifier = Modifier
//            .fillMaxWidth()
//            .height(8.dp))
//
//        LargeButton(
//            btnText = stringResource(R.string.text_login_btn_kakao),
//            btnIdx = 2, // 카카오,
//            buttonAction = {
//
//            }
//        )
//        Spacer(modifier = Modifier
//            .fillMaxWidth()
//            .height(8.dp))

//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//            navController.navigate(PageRoutes.Home.route) }) {
//            Text("홈페이지 이동")
//            GlobalState.isLogin = true
//        }

    }

}