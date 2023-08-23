package com.artist.wea.pages

import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.WeaRegex
import java.util.regex.Pattern

@Composable
fun FindPwdPage(
    navController: NavHostController,
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
            pageTitle = "비밀번호 찾기"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp, 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 입력 값 저장할 변수
            val nameInputText = remember { mutableStateOf("") }
            val idInputText = remember { mutableStateOf("") }
            val emailInputText = remember { mutableStateOf("") }
            val emailCodeText = remember { mutableStateOf("") }


            // guideText
            Text(text = stringResource(id = R.string.text_find_pwd_page_guide),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp),
                style = getDefTextStyle()
                    .copy(color= colorResource(id = R.color.mono700)),
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
                    })
            }
            Spacer(modifier = Modifier.height(32.dp))
            LargeButton(
                btnText = stringResource(R.string.text_btn_find_pwd),
                buttonAction = {
                    val name = nameInputText.value
                    val id = idInputText.value
                    val email = emailCodeText.value
                    viewModel.findUserPassword(name = name, email = email, id = id)
                    viewModel.findUserPasswordRes.observe(mOwner, Observer {
                        Log.d("FIND_PWD_RES:::", "${it.toString()}")
                    })

                }
            )
        }
    }


}