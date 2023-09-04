package com.artist.wea.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.SeeInviteComponent
import com.artist.wea.components.SendInviteComponent
import com.artist.wea.components.data.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemberAddPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize()
){
    // 탭 items
    val tabs = listOf(
        TabItem(
            title = stringResource(R.string.text_member_add_title),
            screen = {
                SendInviteComponent(
                    navController = navController
                )
            }
        ),
        TabItem(
            title = stringResource(R.string.text_member_add_status),
            screen = {
                SeeInviteComponent(
                    navController = navController
                )
            }
        )
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    // 멤버 추가 페이지 본체
    Column (
        modifier = modifier
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_member_add),
        )
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
    }
}