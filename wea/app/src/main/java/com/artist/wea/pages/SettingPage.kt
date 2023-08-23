package com.artist.wea.pages

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.SettingItem
import com.artist.wea.constants.getDefTextStyle

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
            pageTitle = "환경 설정",
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
                optionName = "푸시 알림",
                description = "앱 내에서 PUSH 알람을 보냅니다."
            )
            SettingItem(
                optionName = "위치 서비스",
                description = "가까운 공연장, 공연 정보 조회 중 위치 기반 서비스를 이용할 수 있습니다."
            )
            SettingItem(
                optionName = "버전 정보",
                description = "현재 최신 버전입니다.",
                versionText = "1.0.0"
            )
            // 이용약관
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(0.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text = "이용 약관", style = getDefTextStyle())
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "약관 더보기",
                    tint = colorResource(id = R.color.mono700)
                )
            }
        }
    }
    
}
