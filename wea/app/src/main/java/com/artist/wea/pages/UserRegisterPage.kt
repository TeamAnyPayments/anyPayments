package com.artist.wea.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.components.CheckBoxScreen
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.EmailGuidText
import com.artist.wea.components.LargeButton
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.PageRoutes

@Composable
fun UserRegisterPage(
    navController: NavController,
    type:String?
){

    val scrollState = rememberScrollState()

    Column (modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp, 12.dp)
        .verticalScroll(scrollState)
    ){
        // 회원가입 양식에서 사용자로부터 입력 받을 값들
        var nameInputText = remember { mutableStateOf("") }
        var idInputText = remember { mutableStateOf("") }
        var pwdInputText = remember { mutableStateOf("") }
        var checkPwdText = remember { mutableStateOf("") }
        var emailText = remember { mutableStateOf("") }
        var emailCodeText = remember { mutableStateOf("") }

        // Text(text = type.toString()) // temp

        // 이름
        nameInputText.value =
            TitleInputForm(
                titleText = stringResource(R.string.text_name_label),
                hintText = stringResource(R.string.text_name_input_guide)
            );

        // 아이디, 중복 체크 진행!
        idInputText.value = DuplicateCheckInputForm(
            titleText = stringResource(id = R.string.text_id_label),
            hintText = stringResource(id = R.string.text_id_input_guide)
        )

        // 비밀번호 입력
        pwdInputText.value =  TitleInputForm(
            titleText = stringResource(id = R.string.text_pwd_label),
            hintText = stringResource(id = R.string.text_pwd_input_guide)
        )

        // 비밀번호 일치 체크
        checkPwdText.value = TitleInputForm(
            titleText = stringResource(R.string.text_pwd_check_label),
            hintText = stringResource(R.string.text_pwd_check_guide)
        )

        if(type.equals("common")){
            // 이메일 입력
            emailText.value = DuplicateCheckInputForm(
                titleText = stringResource(R.string.text_email_label),
                hintText = stringResource(R.string.text_email_guide),
                btnText = stringResource(R.string.text_verify_email)
            )

            // 이메일 인증 트리거 발생시 UI 동적으로 렌더링
            if(emailText.value.isNotEmpty()){ // 임시 조건!
                emailCodeText.value = VerifyInputForm(
                    verifyText = stringResource(id = R.string.text_verify_code),
                    hintText = stringResource(id = R.string.text_verify_code_guide)
                )
            }
            // guideText
            EmailGuidText()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 약관 동의 컴포넌트
        CheckBoxScreen(navController = navController);
        Spacer(modifier = Modifier.height(16.dp))

        // 회원가입 버튼
        LargeButton(
            btnText= stringResource(R.string.text_user_regist_btn),
            navController=navController,
            nextPage = PageRoutes.Login.route
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}
