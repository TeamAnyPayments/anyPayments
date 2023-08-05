package com.artist.wea.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.AlarmItem
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.uidtclass.AlarmData

@Composable
fun NotifyPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize()
){

    // 테스트용 더미데이터
    val alramList = mutableListOf<AlarmData>(
        AlarmData(
            iconImg = Icons.Filled.Notifications,
            content = "개인정보 처리 방침이 변경되었습니다.",
            isChek = false,
            alarmColor = colorResource(id = R.color.pastel_pink100),
            contentColor = colorResource(id = R.color.red500)
        ),
        AlarmData(
            iconImg = Icons.Filled.Info,
            content = "무슨무슨 공연 후원에 성공했습니다. 이제 티켓 페이지에서 공연 티켓을 확인할 수 있습니다.",
            isChek = false,
            alarmColor = colorResource(id = R.color.mono100),
            contentColor = colorResource(id = R.color.mono600)
        ),
        AlarmData(
            iconImg = Icons.Filled.CheckCircle,
            content = "무슨무슨 아티스트님이 공연 후기를 남겼어요 지금 바로 확인해보세요!",
            isChek = false,
            alarmColor = colorResource(id = R.color.sky_blue100),
            contentColor = colorResource(id = R.color.sky_blue600)
        ),
        AlarmData(
            iconImg = Icons.Filled.Email,
            content = "우리동네 아티스트 팀에서 보내온 초대가 도착했어요. 수락하시겠습니까?",
            isChek = true,
            alarmColor = colorResource(id = R.color.pastel_yellow100),
            contentColor = colorResource(id = R.color.brown700),
            checkColor = colorResource(id = R.color.pastel_green200)
        )
    )

    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        PageTopBar(
            navController = navController,
            pageTitle = "알림 센터",
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            alramList.forEach { item ->
                AlarmItem(
                    navController = navController,
                    alarmData = item
                )
            }
        }
    }
}