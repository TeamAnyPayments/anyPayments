package com.artist.wea.pages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.CheckBoxScreen
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.EmailGuidText
import com.artist.wea.components.InputForm
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getButtonColor
import com.artist.wea.constants.getDefTextStyle
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
            val nameInputText = remember { mutableStateOf("") }
            val idInputText = remember { mutableStateOf("") }
            val pwdInputText = remember { mutableStateOf("") }
            val checkPwdText = remember { mutableStateOf("") }
            val emailText = remember { mutableStateOf("") }
            val emailCodeText = remember { mutableStateOf("") }
            val idCheckResult = remember { mutableStateOf(false) } // 아이디 중복체크
            val codeVerifyResult = remember { mutableStateOf(false) } // 코드 인증
            val isAgreeRequiredTerms = remember { mutableStateOf(false) } // 약관 동의여부


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


            // 아이디 중복체크 가이드 텍스트 표시 변수
            val isUsableId = remember { mutableStateOf(false) }
            val btnFLag = remember { mutableStateOf(0) }
            // 아이디, 중복 체크 진행!
            idInputText.value = DuplicateCheckInputForm(
                titleText = stringResource(id = R.string.text_id_label),
                hintText = stringResource(id = R.string.text_id_input_guide),
                isError = idInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.idPattern.pattern(), idInputText.value),
                errorText = WeaRegex.idGuideText,
                buttonAction = {
                    // 아이디 중복 체크
                    viewModel.checkUserId(idInputText.value)
                    viewModel.checkUserIdRes.observe(mOwner, Observer {
                        if(!it){
                            idCheckResult.value = true
                            isUsableId.value = true
                            btnFLag.value = 0
                        }else {
                            idCheckResult.value = false
                            isUsableId.value = false
                            btnFLag.value = 1
                        }
                    })
                },
                isSuccess = isUsableId.value,
                onTextChange = {
                    idCheckResult.value = false
                    isUsableId.value = false
                    btnFLag.value = 0;
                },
                btnFlag = btnFLag.value,
                successText = "사용할 수 있는 아이디 입니다.",
                failText = "사용할 수 없는 아이디 입니다."
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
                    isError = emailText.value.isNotEmpty()
                            && !Pattern.matches(WeaRegex.emailPattern.pattern(), emailText.value),
                    errorText = WeaRegex.emailGuideText,
                    buttonAction = {
                        if(Pattern.matches(WeaRegex.emailPattern.pattern(), emailText.value)){ // 이메일이 유효할 경우
                            isMatched.value = true // 코드 발송 버튼 활성화
                            viewModel.sendCodeToEmail(emailText.value);
                            // 이메일 전송 결과
                            viewModel.sendCodeToEmailRes.observe(mOwner, Observer {
                                if(!it){
                                    Toast.makeText(context,"인증 코드가 발송되었습니다", Toast.LENGTH_SHORT).show()
                                }
                            })
                        } else { // 이메일이 유효하지 않을 경우
                            Toast.makeText(context, WeaRegex.emailGuideText, Toast.LENGTH_SHORT).show()
                        }
                    },
                    isDisable = !codeVerifyResult.value
                )

                // 이메일 인증 트리거 발생시 UI 동적으로 렌더링
                if(isMatched.value) {
                    emailCodeText.value = VerifyInputForm(
                        verifyText = stringResource(id = R.string.text_verify_code),
                        hintText = stringResource(id = R.string.text_verify_code_guide),
                        isDisable = !codeVerifyResult.value,
                        buttonActions = {
                            // checkEmailByCode
                            viewModel.checkEmailByCode(
                                email = emailText.value,
                                code = emailCodeText.value
                            );

                            viewModel.checkEmailByCodeRes.observe(mOwner, Observer {
                                if (!it) {
                                    codeVerifyResult.value = !it // 코드 인증 처리
                                    Toast.makeText(context, "인증 성공", Toast.LENGTH_SHORT).show()
                                }else {
                                    Toast.makeText(context, "인증 실패", Toast.LENGTH_SHORT).show()
                                }
                            })
                        })
                }
                // guideText
                EmailGuidText()
            }else {
                codeVerifyResult.value = true
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 약관 동의 컴포넌트
            isAgreeRequiredTerms.value = CheckBoxScreen(navController = navController);
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
                        terms = isAgreeRequiredTerms.value,
                        checkId = idCheckResult.value,
                        checkEmail = codeVerifyResult.value
                    )
                    // 모든 약관 및 인증 절차를 통과했는가?
                    val isComplete = jUser.terms && jUser.checkId && jUser.checkEmail
                    if(isComplete){
                        viewModel.joinUser(jUser)
                        viewModel.joinUserRes.observe(mOwner, Observer {
                            if(it){
                                Toast.makeText(context, WeaRegex.joinSuccessGuideText, Toast.LENGTH_SHORT).show()
                                navController.navigate(PageRoutes.Login.route)
                            }else {
                                Toast.makeText(context, WeaRegex.joinFailGuideText, Toast.LENGTH_SHORT).show()
                            }
                        })
                    }else {
                        Toast.makeText(context, WeaRegex.joinRejectGuideText, Toast.LENGTH_SHORT).show()
                    }
                }
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

