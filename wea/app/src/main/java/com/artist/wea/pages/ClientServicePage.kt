package com.artist.wea.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ClientGuideBanner
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getOutlinedTextFieldColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientServicePage(
    navController: NavHostController
){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.mono50)))
    {
        var text by remember { mutableStateOf("") }
        val context = LocalContext.current;
        val sendCompleteText = stringResource(R.string.text_complete_sumbmit_cs)

        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_cs),
            rightMenuText = "전송",
            rightMenuAction = {
                Toast.makeText(context,
                    sendCompleteText, Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        )
        // guide Banner
        ClientGuideBanner()

        // 입력
        OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("문의 사항") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(192.dp)
                    .padding(16.dp),
            colors =  getOutlinedTextFieldColors()
        )
        // developer info
        val emailAddress = "email@email.com"
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp, 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "개발자 연락처",
                style = get14TextStyle()
                    .copy(colorResource(id = R.color.mono700))
            )
            Text(
                text = "이메일 : $emailAddress",
                style = get14TextStyle()
                    .copy(colorResource(id = R.color.mono700))
            )
        }



    }

}