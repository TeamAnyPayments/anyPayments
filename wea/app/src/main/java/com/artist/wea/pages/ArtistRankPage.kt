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
import androidx.compose.ui.layout.ContentScale
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
            .fillMaxSize()
){

    // 랭크 아이템 (테스트)
    val rankList = mutableListOf<ArtistRankData>(
        ArtistRankData(
            no = 1,
            artistName = "무슨무슨 아티스트",
            artistAddress = "경기 고양시 일산서구",
            artistImgURL = "https://cdnimage.dailian.co.kr/news/202002/news_1581993821_869287_m_1.jpeg"
        ),
        ArtistRankData(
            no = 2,
            artistName = "블라블라 아티스트",
            artistAddress = "서울 특별시 강남구",
            artistImgURL = "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/2Z3MD7RDMQIRSUWM5ASEVKSKAY.jpg"

        ),
        ArtistRankData(
            no = 3,
            artistName = "룰라룰라 아티스트",
            artistAddress = "인천광역시 서구",
            artistImgURL = "https://www.kyongbuk.co.kr/news/photo/202105/2076224_500129_3236.jpg"
        ),
        ArtistRankData(
            no = 4,
            artistName = "훌라훌라 아티스트",
            artistAddress = "경기 안산시 단원구",
            artistImgURL = "https://cdn.dkilbo.com/news/photo/202103/328795_222527_1909.jpg"
        ),
        ArtistRankData(
            no = 5,
            artistName = "눈누난나 아티스트",
            artistAddress = "경기 수원시 권선구",
            artistImgURL = "https://news.tbs.seoul.kr/Upload/Image/20221202/00000000000001308345.jpg"
        ),
        ArtistRankData(
            no = 6,
            artistName = "우리동네 아티스트",
            artistAddress = "서을 특별시 관악구",
            artistImgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTyemqDmEPapQHDeVBwwFuMZeTkp97FvTryJA&usqp=CAU"
        )
    )

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val defImgID = R.drawable.default_image
            val imgList = arrayOf(
                "https://www.nps.or.kr/html/main_2022/images/photo/main/banner8.png",
                "https://d1orkkc34keaka.cloudfront.net/images/2021/08/24/1629783028_8DwfsnNsREK8LxQR5BfDasucDAUaFtsaMMR1cp0u.jpeg"
            )
            // 랭킹 쪽 컨셉 이미지 뷰어
            GlideImage(
                imageModel = if(isRealTime) imgList[0] else imgList[1],
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 100),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = painterResource(id = defImgID),
                // shows an error ImageBitmap when the request failed.
                error = painterResource(id = defImgID),
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
