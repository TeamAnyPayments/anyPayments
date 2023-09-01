package com.artist.wea.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.components.AlarmItem
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.DummyValues

@Composable
fun NotifyPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize()
){

    // 테스트용 더미데이터
    val alramList = DummyValues().getAlarmList()

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