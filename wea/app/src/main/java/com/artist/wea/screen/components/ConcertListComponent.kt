package com.artist.wea.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues
import com.artist.wea.data.ConcertListInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 콘서트 아이템을 담을 리스트 컴포저블
// 콘서트 정보 검색 시 사용 됨
@Composable
fun ConcertListComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    // 콘서트 정보를 담은 아이템 리스트 데이터
    concertList: MutableCollection<ConcertListInfo> = DummyValues.concertList.values
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        var concertList = DummyValues.concertList.values
        val popList = concertList.sortedByDescending { it.concertId }
        val nameList = concertList.sortedBy { it.artistName }
        val dateList = concertList.sortedBy { it.location }

        val currentList = remember { mutableStateOf<Collection<ConcertListInfo>>(concertList) }

        // 검색...
        val searchText = remember { mutableStateOf<String>("") }
        val searchList = remember { mutableStateOf<Collection<ConcertListInfo>>(listOf()) }
        // debouce 변수
        // 더블클릭 방지를 위한 Job 변수
        var debounceJob: Job? = null

        // 검색 창
        searchText.value = SearchBar(
            searchOptions = arrayOf("선택안함", "거리순", "최신순", "인기순"), // 검색 옵션
            sortAction = arrayOf(
                { currentList.value = concertList},
                { currentList.value = popList },
                { currentList.value = nameList },
                { currentList.value = dateList }
            ),
            searchAction = {
                if(searchText.value.isNotEmpty()){
                    debounceJob?.cancel()
                    debounceJob = GlobalScope.launch {
                        delay(300)
                        val filteredList = currentList.value.filter { concertInfo ->
                            concertInfo.artistName.contains(searchText.value, ignoreCase = true)
                        }
                        searchList.value = filteredList
                    }
                }
            }

        )

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if(!searchText.value.isEmpty() && searchList.value.isEmpty()){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp)){
                    ReplaceImage(
                        imageModel = R.drawable.icon_no_search,
                        modifier = Modifier
                            .width(256.dp)
                            .height(128.dp)
                            .align(Alignment.Center)
                    )
                }
            } else if(searchList.value.isEmpty()){
                currentList.value.forEach{ concertInfo ->
                    ConcertSearchItem(
                        navController = navController,
                        content = concertInfo,
                        // 더미데이터 하나에 대해서만 조회 되도록 잠시 막아둠!
                        isActive = concertInfo.concertId == DummyValues.defConcertInfo.concertId // TODO... ANR 이슈 해결하고 지우기!
                    )
                }
            }else {
                searchList.value.forEach{ concertInfo ->
                    ConcertSearchItem(
                        navController = navController,
                        content = concertInfo,
                        // 더미데이터 하나에 대해서만 조회 되도록 잠시 막아둠!
                        isActive = concertInfo.concertId == DummyValues.defConcertInfo.concertId // TODO... ANR 이슈 해결하고 지우기!
                    )
                }
            }
        }
    }
}