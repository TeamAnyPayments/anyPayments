package com.artist.wea.pages

// import com.artist.wea.util.WeaTimer
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
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
import com.artist.wea.components.CheckBoxScreen
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.EmailGuidText
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.data.JoinUser
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.WeaRegex
import com.artist.wea.util.WeaRegex.Companion.parseToTimeString
import kotlinx.coroutines.delay
import java.util.regex.Pattern
import kotlin.time.Duration.Companion.seconds


@Composable
fun UserRegisterPage(
    navController: NavHostController,
    type:String?
){

    // 비동기 통신을 위한 기본 객체 settings
    val context = LocalContext.current;
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
            .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ){
            // 회원가입 양식에서 사용자로부터 입력 받을 값들
            val nameInputText = remember { mutableStateOf("") }
            val idInputText = remember { mutableStateOf("") }
            val pwdInputText = remember { mutableStateOf("") }
            val checkPwdText = remember { mutableStateOf("") }
            val emailInputText = remember { mutableStateOf("") }
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
                val timerDefault = 300
                val timerSecond = remember { mutableStateOf(0) }

                // 이메일 입력
                emailInputText.value = DuplicateCheckInputForm(
                    titleText = stringResource(R.string.text_email_label),
                    hintText = stringResource(R.string.text_email_guide),
                    btnText = if(isDisable.value) stringResource(R.string.text_verify_email) else "인증 완료",
                    isError = emailInputText.value.isNotEmpty()
                            && !Pattern.matches(WeaRegex.emailPattern.pattern(), emailInputText.value),
                    errorText = WeaRegex.emailGuideText,
                    buttonAction = {
                        if(Pattern.matches(WeaRegex.emailPattern.pattern(), emailInputText.value)){ // 이메일이 유효할 경우
                            isMatched.value = true // 코드 발송 버튼 활성화
                            timerSecond.value = timerDefault
                            viewModel.sendCodeToEmail(emailInputText.value);
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
                                email = emailInputText.value,
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
                        },
                        second = timerSecond.value
                    )
                    // 코드발송 시 타이머 재시작 하게 하는 부분
                    if(isMatched.value && timerSecond.value > 0){
                        // Timer...
                        LaunchedEffect(Unit) { // 인증 폼의 타이머
                            while(timerSecond.value > 0 && !codeVerifyResult.value) {
                                delay(1.seconds)
                                timerSecond.value--
                            }
                        }
                    }
                    if(!codeVerifyResult.value){
                        Text(
                            text = parseToTimeString(timerSecond = timerSecond),
                            style = get14TextStyle()
                                .copy(
                                    textAlign = TextAlign.Right
                                ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                    }
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
                        email = emailInputText.value,
                        terms = isAgreeRequiredTerms.value,
                        checkId = idCheckResult.value,
                        checkEmail = codeVerifyResult.value
                    )
                    // 모든 약관 및 인증 절차를 통과했는가?
                    val isComplete = jUser.terms && jUser.checkId && jUser.checkEmail
                    if(isComplete){
                        viewModel.joinUser(jUser)
                        viewModel.joinUserRes.observe(mOwner, Observer {
                            if(!it){
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
