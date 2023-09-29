package com.artist.wea.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.AlarmItem
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.data.AlarmData
import com.artist.wea.constants.DummyValues

// 알림 페이지
@Composable
fun NotifyPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize(),
    alarmList: MutableList<AlarmData> = DummyValues().getAlarmList() // 테스트용 더미데이터
){

    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_notify),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            alarmList.forEach { item ->
                AlarmItem(
                    navController = navController,
                    alarmData = item
                )
            }
        }
    }
}