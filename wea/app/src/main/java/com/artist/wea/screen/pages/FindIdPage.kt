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
import com.artist.wea.screen.components.LargeButton
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.TitleInputForm
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.FindIdData
import com.artist.wea.network.model.RegisterViewModel
import com.artist.wea.network.repository.RegisterRepository
import com.artist.wea.util.ToastManager.Companion.shortToast
import com.artist.wea.util.WeaRegex
import java.util.regex.Pattern

// 아이디 찾기 페이지
@Composable
fun FindIdPage(
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
            pageTitle = stringResource(R.string.text_pgname_find_id)
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
            var nameInputText = remember { mutableStateOf("") }
            var emailInputText = remember { mutableStateOf("") }

            // guideText
            Text(text = stringResource(R.string.text_find_id_page_guide),
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
            nameInputText.value = TitleInputForm(
                titleText = stringResource(id = R.string.text_name_label),
                hintText = stringResource(id = R.string.text_name_input_guide),
                isError = nameInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.namePattern.pattern(), nameInputText.value),
                errorText = WeaRegex.nameGuideText
            )
            emailInputText.value = TitleInputForm(
                titleText = stringResource(id = R.string.text_email_label),
                hintText = stringResource(id = R.string.text_email_guide),
                isError = emailInputText.value.isNotEmpty()
                        && !Pattern.matches(WeaRegex.emailPattern.pattern(), emailInputText.value),
                errorText = WeaRegex.emailGuideText
            )
            Spacer(modifier = Modifier.height(32.dp))

            val sendEmailText = stringResource(R.string.text_send_id_to_email)
            val errorEmailText = stringResource(R.string.text_err_send_email)

            LargeButton(
                btnText = stringResource(R.string.text_btn_find_id),
                buttonAction = {
                    // 사용자 이름과 이메일 정보를 받는다.
                    val findIdData = FindIdData(
                        name = nameInputText.value,
                        email = emailInputText.value
                    )
                    viewModel.findUserId(findIdData)
                    viewModel.findUserIdRes.observe(mOwner, Observer {
                        if(!it){
                            // 이메일 전송 성공 Toast
                            shortToast(context, sendEmailText)
                            navController.popBackStack()
                        }else {
                            // 이메일 전송 실패 Toast
                            shortToast(context, errorEmailText)
                        }
                    })
                }
            )
        }
    }

}