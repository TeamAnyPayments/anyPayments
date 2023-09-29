package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.constants.DummyValues
import com.artist.wea.data.ConcertListInfo

// 콘서트 아이템을 담을 리스트 컴포저블
// 콘서트 정보 검색 시 사용 됨
@Composable
fun ConcertListComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    // 콘서트 정보를 담은 아이템 리스트 데이터
    concertList: MutableCollection<ConcertListInfo> = DummyValues().concertList.values
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 검색 창
        SearchBar(
            searchOptions = arrayOf("선택안함", "거리순", "최신순", "인기순") // 검색 옵션
        )

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            concertList.forEach {
                    item ->
                ConcertSearchItem(
                    navController = navController,
                    content = item,
                    // 더미데이터 하나에 대해서만 조회 되도록 잠시 막아둠!
                    isActive = item.concertId == DummyValues().defConcertInfo.concertId // TODO... ANR 이슈 해결하고 지우기!
                )
            }
        }
    }
}