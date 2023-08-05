package com.artist.wea.pages

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle

@Composable
fun FindPwdPage(
    navController: NavHostController,
){
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
            var idText = remember { mutableStateOf("") }
            var emailText = remember { mutableStateOf("") }
            var codeText = remember { mutableStateOf("") }

            // guideText
            Text(text = stringResource(id = R.string.text_find_pwd_page_guide),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp),
                style = getDefTextStyle().copy(color= colorResource(id = R.color.mono700))
            )

            Spacer(modifier = Modifier.height(16.dp))
            idText.value = TitleInputForm(
                titleText = stringResource(id = R.string.text_id_label),
                hintText = stringResource(id = R.string.text_id_input_guide)
            )

            emailText.value = DuplicateCheckInputForm(
                titleText = stringResource(id = R.string.text_email_label),
                hintText = stringResource(id = R.string.text_email_guide),
                btnText = stringResource(id = R.string.text_verify_email),
                navController = navController
            )

            if(emailText.value.isNotEmpty()){
                codeText.value = VerifyInputForm(
                    verifyText = stringResource(id = R.string.text_value_verify),
                    hintText = stringResource(id = R.string.text_verify_code_guide),
                    btnText = stringResource(id = R.string.text_value_verify)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            LargeButton(
                btnText = stringResource(R.string.text_btn_find_pwd),
                navController = navController,
                nextPage = PageRoutes.ChangePwd.route )
        }
    }


}