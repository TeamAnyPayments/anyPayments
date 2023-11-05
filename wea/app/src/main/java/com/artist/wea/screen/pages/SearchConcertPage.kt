package com.artist.wea.screen.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.screen.components.ConcertListComponent
import com.artist.wea.screen.components.PageTopBar

// 콘서트 검색 페이지
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchConcertPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize()
){
    /*
    // 탭 items
    val tabs = listOf(
        TabItem(
            title = stringResource(R.string.text_tab_see_map),
            icon = Icons.Filled.Place,
            screen = {
                NaverMapComponent(
                    navController = navController,
                    modifier = Modifier.fillMaxSize(),
                    hasMarkList = true
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.text_tab_see_list),
            icon = Icons.Filled.List,
            screen = {
                ConcertListComponent(
                    navController = navController,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        )
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

     */

    // 지도찾기 페이지 본체
    Column (
        modifier = modifier
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_find_concert),
        )

        // 네이버 맵 마커 여러개 찍는 방법을 모르겠어서 일단 하나로 합침
        /*
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = colorResource(id = R.color.red500)
                )
            }
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = item.title) },
                    icon = { item.icon?.let { Icon(it,  "") } },
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    selectedContentColor = colorResource(id = R.color.black),
                    unselectedContentColor = colorResource(id = R.color.mono700),
                    interactionSource = remember { MutableInteractionSource() },
                    modifier = Modifier.background(colorResource(id = R.color.mono50))
                )
            }
        }
        // Horizontal Pager here
        HorizontalPager(
            pageCount = tabs.size,
            state = pagerState
        ) {
            tabs[pagerState.currentPage].screen()
        }
         */
        ConcertListComponent(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
        )
    }

}
