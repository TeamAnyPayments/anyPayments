package com.artist.wea.screen.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.artist.wea.BuildConfig
import com.artist.wea.R
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.SettingItem
import com.artist.wea.constants.getDefTextStyle

// 환경 설정 페이지
@Composable
fun SettingPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize()
){
    Column(
        modifier = modifier
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_setting),
        )
        //body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "알림 설정",
                style = getDefTextStyle()
                    .copy(
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp
                    ),
                modifier = Modifier.fillMaxWidth()
            )
            SettingItem(
                optionName = stringResource(R.string.text_title_opt_push),
                description = stringResource(R.string.text_hint_opt_push)
            )
            SettingItem(
                optionName = stringResource(R.string.text_title_opt_location),
                description = stringResource(R.string.text_hint_opt_location)
            )
            SettingItem(
                optionName = stringResource(R.string.text_title_opt_version),
                description = stringResource(R.string.text_hint_opt_version),
                versionText = BuildConfig.VERSION_NAME.toString() // 현재 앱 버전
            )
            // 이용약관
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(0.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(R.string.text_user_policy),
                    style = getDefTextStyle()
                )
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(R.string.text_see_more_policy),
                    tint = colorResource(id = R.color.mono700)
                )
            }
        }
    }
    
}
