package com.artist.wea.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.CheckBoxScreen
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.EmailGuidText
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.PageRoutes
import com.artist.wea.data.JoinUser
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.WeaRegex
import java.util.regex.Pattern

@Composable
fun UserRegisterPage(
    navController: NavHostController,
    type:String?
){

    val context = LocalContext.current;
    // heartRate 세팅
    val mOwner = LocalLifecycleOwner.current
    val repository = RegisterRepository()
    val viewModel = RegisterViewModel(repository)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.mono50)))
    {
        PageTopBar(
            navController = navController,
            pageTitle = "회원 가입"
        )

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
                    hintText = stringResource(R.string.text_name_input_guide),
                    isError = nameInputText.value.isNotEmpty()
                            && !Pattern.matches(WeaRegex.namePattern.pattern(), nameInputText.value),
                    errorText = WeaRegex.nameGuideText
                );

            // 아이디, 중복 체크 진행!
            idInputText.value = DuplicateCheckInputForm(
                titleText = stringResource(id = R.string.text_id_label),
                hintText = stringResource(id = R.string.text_id_input_guide),
                isError = idInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.idPattern.pattern(), idInputText.value),
                errorText = WeaRegex.idGuideText,
                buttonAction = {
//                    val cui = CheckUserId(idInputText.value);
//                    viewModel.checkUserId(cui)
//                    viewModel.checkUserIdRes.observe(mOwner, Observer {
//                        Log.d("CHECK_ID", it.toString());
//                    })
                }

            )

            // 비밀번호 입력
            pwdInputText.value =  TitleInputForm(
                titleText = stringResource(id = R.string.text_pwd_label),
                hintText = stringResource(id = R.string.text_pwd_input_guide),
                isError = pwdInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.pwdPattern.pattern(), pwdInputText.value),
                errorText = WeaRegex.pwdGuideText,
                isPassword = true
            )

            // 비밀번호 일치 체크
            checkPwdText.value = TitleInputForm(
                titleText = stringResource(R.string.text_pwd_check_label),
                hintText = stringResource(R.string.text_pwd_check_guide),
                isError = checkPwdText.value.isNotEmpty()
                        && pwdInputText.value != checkPwdText.value,
                errorText = WeaRegex.pwdDiffText,
                isPassword = true
            )

            if(type.equals("common")){
                val isMatched = remember { mutableStateOf(false) }
                val isDisable = remember { mutableStateOf(true) }

                // 이메일 입력
                emailText.value = DuplicateCheckInputForm(
                    titleText = stringResource(R.string.text_email_label),
                    hintText = stringResource(R.string.text_email_guide),
                    btnText = if(isDisable.value) stringResource(R.string.text_verify_email) else "인증 완료",
                    btnColor = if(isDisable.value) colorResource(id = R.color.dark_orange300)
                                else colorResource(id = R.color.mono300),
                    isError = emailText.value.isNotEmpty()
                            && !Pattern.matches(WeaRegex.emailPattern.pattern(), emailText.value),
                    errorText = WeaRegex.emailGuideText,
                    buttonAction = {
                        if(Pattern.matches(WeaRegex.emailPattern.pattern(), emailText.value)){
                            isMatched.value = true // 코드 발송 버튼 활성화
                        }else {
                            Toast.makeText(context, WeaRegex.emailGuideText, Toast.LENGTH_SHORT).show()
                        }
//                        val emailObject = JSONObject()
//                        emailObject.put("email", emailText.value)
//                        viewModel.sendCodeToEmail(emailObject);
//                        viewModel.sendCodeToEmailRes.observe(mOwner, Observer {
//                            Log.d("SEND_EMAIL", it.toString());
//                        })
                    },
                    isDisable = isDisable.value
                )

                // 이메일 인증 트리거 발생시 UI 동적으로 렌더링
                if(isMatched.value){
                    emailCodeText.value = VerifyInputForm(
                        verifyText = stringResource(id = R.string.text_verify_code),
                        hintText = stringResource(id = R.string.text_verify_code_guide),
                        isDisable = isDisable.value,
                        buttonActions = {
                            isDisable.value = emailCodeText.value.isEmpty()
                        }
                    )
                }
                // guideText
                EmailGuidText()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 약관 동의 컴포넌트
            val isAllCheck = CheckBoxScreen(navController = navController);
            Spacer(modifier = Modifier.height(16.dp))

            // 회원가입 버튼
            LargeButton(
                btnText= stringResource(R.string.text_user_regist_btn),
                buttonAction = {
                    val jUser = JoinUser(
                        id = idInputText.value,
                        password = pwdInputText.value,
                        name = nameInputText.value,
                        email = emailText.value,
                        terms = isAllCheck,
                        checkId = true,
                        checkEmail = true
                    )
                    if(isAllCheck){
                        viewModel.joinUser(jUser)
                        viewModel.joinUserRes.observe(mOwner, Observer {
                            if(it){
                                Toast.makeText(context, WeaRegex.JoinSuccessGuideText, Toast.LENGTH_SHORT).show()
                                navController.navigate(PageRoutes.Login.route)
                            }
                        })
                    }else {
                        Toast.makeText(context, WeaRegex.JoinRejectGuideText, Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}
