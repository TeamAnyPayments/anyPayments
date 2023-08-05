package com.artist.wea.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ArtistRankItem
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.uidtclass.ArtistRankData
import com.artist.wea.components.uidtclass.TabItem
import com.artist.wea.constants.get12TextStyle
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtistRankPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize()
){

    // 랭크 아이템 (테스트)
    val rankList = mutableListOf<ArtistRankData>(
        ArtistRankData(
            no = 1,
            artistName = "무슨무슨 아티스트",
            artistAddress = "경기 고양시 일산서구",
            artistImg = painterResource(id = R.drawable.icon_def_user_img),
        ),
        ArtistRankData(
            no = 2,
            artistName = "블라블라 아티스트",
            artistAddress = "서울 특별시 강남구",
            artistImg = painterResource(id = R.drawable.icon_def_user_img),
        ),
        ArtistRankData(
            no = 3,
            artistName = "룰라룰라 아티스트",
            artistAddress = "인천광역시 서구",
            artistImg = painterResource(id = R.drawable.icon_def_user_img),
        ),
        ArtistRankData(
            no = 4,
            artistName = "훌라훌라 아티스트",
            artistAddress = "경기 안산시 단원구",
            artistImg = painterResource(id = R.drawable.icon_def_user_img),
        )
    )
    rankList.addAll(rankList.size, rankList);

    // 탭 items
    val tabs = listOf(
        TabItem(
            title = "실시간 베스트",
            screen = {
                RankTabScreen(
                    navController = navController,
                    updateDate = LocalDateTime.now(),
                    isRealTime = true,
                    rankList = rankList
                )
            }
        ),
        TabItem(
            title = "주간 베스트",
            screen = {
                RankTabScreen(
                    navController = navController,
                    updateDate = LocalDateTime.now(),
                    rankList = rankList
                )
            }
        ),
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    // 랭킹 페이지 본체
    Column (
        modifier = modifier
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = "아티스트 순위",
        )
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
            
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

@Composable
fun RankTabScreen(
    navController: NavHostController,
    updateDate: LocalDateTime,
    isRealTime:Boolean = false,
    rankList:MutableList<ArtistRankData> = mutableListOf()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        updateDate.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

        // 표시될 문자열
        val displayUpdateString =
            if(isRealTime) updateDate
                            .toLocalTime()
                            .format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                            .toString()
            else updateDate
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern("yyyy. MM. dd."))
                .toString()

        Text(
            text = displayUpdateString.plus(" 에 업데이트 함"),
            style = get12TextStyle().copy(
                color = colorResource(id = R.color.mono600),
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp)
        )

        // RankList
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp, 4.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rankList.forEachIndexed { index, artistRankData ->
                ArtistRankItem(
                    navController = navController,
                    no = artistRankData.no,
                    artistName = artistRankData.artistName,
                    artistAddress = artistRankData.artistAddress,
                    artistImg = artistRankData.artistImg,
                )
            }
            Spacer(modifier = Modifier.height(24.dp)) // space
        }
    }
}
