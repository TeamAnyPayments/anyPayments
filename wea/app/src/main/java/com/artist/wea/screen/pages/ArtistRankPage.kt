package com.artist.wea.screen.pages

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.data.ArtistRankData
import com.artist.wea.data.TabItem
import com.artist.wea.screen.components.ArtistRankItem
import com.artist.wea.screen.components.PageTopBar
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtistRankPage(
    navController: NavHostController,
    modifier: Modifier =
        Modifier
            .fillMaxSize(),
    // 랭크 아이템 리스트
    rankList: MutableList<ArtistRankData> = DummyValues.rankList
){

    // 탭 items
    val tabs = listOf(
        TabItem(
            title = stringResource(R.string.text_tab_real_rank),
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
            title = stringResource(R.string.text_tab_week_rank),
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val defImgID = R.drawable.default_image
            val imgList = arrayOf(
                "https://www.wevity.com/upload/contest/20220810154219_bd24179e.jpg",
                "https://tr.xza.kr/imgdata/tr_xza_kr/202308/20230813135503-5792.jpg"
            )
            // 랭킹 쪽 컨셉 이미지 뷰어
            GlideImage(
                imageModel = if(isRealTime) imgList[0] else imgList[1],
                contentScale = ContentScale.Crop, // 이미지 ContentScale
                circularReveal = CircularReveal(duration = 100),
                placeHolder = painterResource(id = defImgID), // place holder
                error = painterResource(id = defImgID), // 에러 이미지
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
            )
            rankList.forEachIndexed { index, artistRankData ->
                ArtistRankItem(
                    navController = navController,
                    artistRankData = artistRankData,
                )
            }
            Spacer(modifier = Modifier.height(24.dp)) // space
        }
    }
}
