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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.DuplicateCheckInputForm
import com.artist.wea.components.EmailGuidText
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.VerifyInputForm
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ChangeEmailPage(
    navController: NavHostController,
) {

    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .background(color = colorResource(id = R.color.mono50)))
    {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_change_email),
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
            var emailText = remember { mutableStateOf("") }
            var codeText = remember { mutableStateOf("") }

            // guideText
            Text(text = stringResource(id = R.string.text_change_email_page_guide),
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

            emailText.value = DuplicateCheckInputForm(
                titleText = stringResource(id = R.string.text_email_label),
                hintText = stringResource(id = R.string.text_email_guide),
                btnText = stringResource(id = R.string.text_verify_email),
            )

            if(emailText.value.isNotEmpty()){
                codeText.value = VerifyInputForm(
                    verifyText = stringResource(id = R.string.text_value_verify),
                    hintText = stringResource(id = R.string.text_verify_code_guide),
                    btnText = stringResource(id = R.string.text_value_verify)
                )
            }
            EmailGuidText();
            Spacer(modifier = Modifier.height(32.dp))
            LargeButton(
                btnText = stringResource(id = R.string.text_btn_change_email),
                buttonAction = {

                }
            )

        }
    }
}