package com.artist.wea.screen.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.FindPwdData
import com.artist.wea.network.model.RegisterViewModel
import com.artist.wea.network.repository.RegisterRepository
import com.artist.wea.screen.components.DuplicateCheckInputForm
import com.artist.wea.screen.components.LargeButton
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.TitleInputForm
import com.artist.wea.screen.components.VerifyInputForm
import com.artist.wea.util.ToastManager.Companion.shortToast
import com.artist.wea.util.WeaRegex
import java.util.regex.Pattern

// 비밀번호 찾기 페이지
@Composable
fun FindPwdPage(
    navController: NavHostController,
){
    // 비동기 통신을 위한 기본 객체 settings
    val context = LocalContext.current;
    val mOwner = LocalLifecycleOwner.current
    val repository = RegisterRepository()
    val viewModel = RegisterViewModel(repository)

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .background(color = colorResource(id = R.color.mono50)))
    {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_find_pwd)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // 입력 값 저장할 변수
            val nameInputText = remember { mutableStateOf("") }
            val idInputText = remember { mutableStateOf("") }
            val emailInputText = remember { mutableStateOf("") }
            val emailCodeText = remember { mutableStateOf("") }


            // guideText
            Text(text = stringResource(id = R.string.text_find_pwd_page_guide),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp),
                style = getDefTextStyle()
                    .copy(
                        color= colorResource(id = R.color.mono700),
                        textAlign = TextAlign.Center
                    ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 이름
            nameInputText.value = TitleInputForm(
                titleText = stringResource(id = R.string.text_name_label),
                hintText = stringResource(id = R.string.text_name_input_guide),
                isError = nameInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.namePattern.pattern(), nameInputText.value),
                errorText = WeaRegex.nameGuideText
            )

            // 아이디 입력
            idInputText.value = TitleInputForm(
                titleText = stringResource(id = R.string.text_id_label),
                hintText = stringResource(id = R.string.text_id_input_guide),
                isError = idInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.idPattern.pattern(), idInputText.value),
                errorText = WeaRegex.idGuideText
            )

            val isMatched = remember { mutableStateOf(false) }
            val isDisable = remember { mutableStateOf(true) }
            val codeVerifyResult = remember { mutableStateOf(false) } // 코드 인증
            val sendCodeText = stringResource(R.string.text_send_code)

            // 이메일 입력
            emailInputText.value = DuplicateCheckInputForm(
                titleText = stringResource(R.string.text_email_label),
                hintText = stringResource(R.string.text_email_guide),
                btnText = if(isDisable.value) stringResource(R.string.text_verify_email) else stringResource(
                    R.string.text_verified_code
                ),
                isError = emailInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.emailPattern.pattern(), emailInputText.value),
                errorText = WeaRegex.emailGuideText,
                buttonAction = {
                    if(Pattern.matches(WeaRegex.emailPattern.pattern(), emailInputText.value)){ // 이메일이 유효할 경우
                        isMatched.value = true // 코드 발송 버튼 활성화
                        viewModel.sendCodeToEmail(emailInputText.value);
                        // 이메일 전송 결과
                        viewModel.sendCodeToEmailRes.observe(mOwner, Observer {
                            if(!it){
                                shortToast(context, text = sendCodeText)
                            }
                        })
                    } else { // 이메일이 유효하지 않을 경우
                        shortToast(context, WeaRegex.emailGuideText)
                    }
                },
                isDisable = !codeVerifyResult.value
            )

            val verifySuccess = stringResource(R.string.text_verify_success)
            val verifyRejected = stringResource(R.string.text_verify_rejected)

            // 이메일 인증 트리거 발생시 UI 동적으로 렌더링
            if(isMatched.value) {
                emailCodeText.value = VerifyInputForm(
                    verifyText = stringResource(id = R.string.text_verify_code),
                    hintText = stringResource(id = R.string.text_verify_code_guide),
                    isDisable = !codeVerifyResult.value,
                    buttonActions = {
                        // checkEmailByCode
                        viewModel.checkEmailByCode(
                            email = emailInputText.value,
                            code = emailCodeText.value
                        );

                        viewModel.checkEmailByCodeRes.observe(mOwner, Observer {
                            if (!it) {
                                codeVerifyResult.value = !it // 코드 인증 처리
                                shortToast(context, verifySuccess);
                            }else {
                                shortToast(context, verifyRejected);
                            }
                        })
                    })
            }
            Spacer(modifier = Modifier.height(32.dp))
            val verifyAlertText = stringResource(R.string.text_alert_verify_email)
            LargeButton(
                btnText = stringResource(R.string.text_btn_find_pwd),
                buttonAction = {
                    // 코드 인증이 됐는지 먼저 점검하고 유효할 경우에만 서버에 비밀번호 찾기 요청
                    if(codeVerifyResult.value){
                        // 비밀번호 찾기 요청 데이터
                        val findPwdData = FindPwdData(
                            name = nameInputText.value,
                            id = idInputText.value,
                            email = emailInputText.value
                        )
                        // TODO.. 서버와 통신하며 기능 완성
                        viewModel.findUserPassword(findPwdData)
                        viewModel.findUserPasswordRes.observe(mOwner, Observer {
                        })
                    }else {
                        shortToast(context, verifyAlertText)
                    }
                }
            )
        }
    }


}