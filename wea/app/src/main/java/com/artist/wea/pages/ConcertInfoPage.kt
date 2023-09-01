package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ArtistInfoItem
import com.artist.wea.components.Badge
import com.artist.wea.components.CompleteTicketDialog
import com.artist.wea.components.ConcertDetailTable
import com.artist.wea.components.GuideBubble
import com.artist.wea.components.InfiniteLoopPager
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.LargeButton
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ArtistInfo
import com.artist.wea.data.BuskingInfo

@Composable
fun ConcertInfoPage(
    navController: NavHostController
){
    // TODO navController를 통해서 공연 데이터를 추출해서 렌더링 하도록 설계
    val buskingInfo = BuskingInfo(
        buskingTitle = "무슨무슨 버스킹",
        buskingImgList = listOf(
            "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151139.jpg",
            "https://i.ytimg.com/vi/udzpnOi63Jg/maxresdefault.jpg",
            "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151132.jpg",
            "https://cdn.skkuw.com/news/photo/201309/10675_5698_2312.jpg",
            "https://cloudfront-ap-northeast-1.images.arcpublishing.com/chosunbiz/FV575KG7PRPH7BVXOULUYOQ5FA.JPG",
            "https://www.kns.tv/news/photo/201704/302921_200430_5649.jpg"
        ),
        buskingIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                "\n" +
                "홍대 스트릿 버스커 그룹 로드 버스킹입니다.\n" +
                "\n" +
                "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                "\n" +
                "음악을 사랑한다는 마음 하나라면,\n" +
                "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                "\n" +
                "오늘 여러분과 함께 특별한 추억 쌓아 보기 위해 특별 버스킹을 열게 되었어요. \n" +
                "\n" +
                "음악을 사랑하는 분이라면 \n" +
                "누구나 함께하실 수 있는 즐거운 시간 보내려고 합니다. \n" +
                "\n" +
                "무더운 여름 로드 버스킹팀과 함께 \n" +
                "시원한 시간을 보내보는건 어떨까요?\n" +
                "\n" +
                "공연에 대한 자세한 정보는 아래를 참조해주세요!",
        genre =  "보컬, 버스킹,",
        startDate = "2023. 07. 21.",
        endDate =  "2023. 07. 23.",
        buskingTime = "13:00 ~ 16:00",
        minSupportAccount = 3000,
        cumulativeAudience = 256,
        tags = listOf("로드버스킹", "스트릿", "홍대"),
        memberList = listOf(
            ArtistInfo(
                profileImgURL = "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                artistName = "블라블라 아티스트",
                comment = "좋은 음악 함께 공유해요"
            ),
            ArtistInfo(
                profileImgURL = "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                artistName = "무슨무슨 아티스트",
                comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
            )
        ),
        locations = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
    )

    val modalVisibleState = remember { mutableStateOf(false) }
    CompleteTicketDialog(
        visible = modalVisibleState.value,
        onDismissRequest = {
            modalVisibleState.value = false
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = buskingInfo.buskingTitle,
            hasBadge =  true,
            badge = {
                Badge(
                    text = "LIVE",
                    color = colorResource(id = R.color.red300)
                )
            }
        )
        Box(modifier = Modifier
            .fillMaxSize()){

            // 공연 body
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(scrollState),
            ) {
                // 공연 이미지
                InfiniteLoopPager(
                    list = buskingInfo.buskingImgList,
                    height = 196.dp
                )
                Spacer(modifier = Modifier.height(16.dp)) // 여백
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(16.dp, 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 공연 소개
                    InfoUnit(
                        titleText="공연 소개",
                        screen = {
                            Text(
                                text = buskingInfo.buskingIntroduce,
                                style = getDefTextStyle()
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // 여백

                    val screenModifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    // 공연 위치
                    InfoUnit(
                        icon = Icons.Filled.Place,
                        modifier = Modifier,
                        titleText = "공연 장소",
                        screen = {
                            Text(
                                text = buskingInfo.locations,
                                style = getDefTextStyle()
                                    .copy(textAlign = TextAlign.Start),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                            )
                            Spacer(modifier = Modifier.height(16.dp)) // 여백
                            // 네이버 맵 올 부분
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(128.dp)
                                .background(color = colorResource(id = R.color.mono100))
                            ){
                                Text(
                                    text = "네이버 지도",
                                    style = get12TextStyle(),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        },
                        screenModifier =  screenModifier
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // 여백

                    // 상세 정보
                    val detailInfoMap:MutableMap<String, String> = mutableMapOf();
                    detailInfoMap["장르"] = buskingInfo.genre
                    detailInfoMap["시작일"] = buskingInfo.startDate.toString()
                    detailInfoMap["종료일"] = buskingInfo.endDate.toString()
                    detailInfoMap["공연 시간"] = buskingInfo.buskingTime.toString()
                    detailInfoMap["최소 후원금$"] = buskingInfo.minSupportAccount.toString()+" 원"
                    detailInfoMap["누적 관람객$"] = buskingInfo.cumulativeAudience.toString()

                    ConcertDetailTable(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        dataMap = detailInfoMap
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // 여백
                    // tags

                    val hScrollState = rememberScrollState();
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .horizontalScroll(hScrollState),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        buskingInfo.tags.forEach { tagName ->
                            Badge(
                                text = "#".plus(tagName),
                                color = colorResource(id = R.color.pastel_yellow100),
                                txtColor = colorResource(id = R.color.black)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp)) // 여백
                    //members
                    InfoUnit(
                        modifier = Modifier,
                        titleText = "Member",
                        screen = {
                            buskingInfo.memberList.forEachIndexed { index, artistInfo ->
                                ArtistInfoItem(
                                    navController = navController,
                                    artistInfo = artistInfo,
                                    hasLine = false
                                )
                            }
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp)) // 여백
                    // GuideBubble
                    GuideBubble()
                    Spacer(modifier = Modifier.height(64.dp)) // 여백
                }
            }

            // 후원 버튼
            LargeButton(
                btnText = "후원하기",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
                    .align(Alignment.BottomEnd),
                buttonAction = {
                    modalVisibleState.value = true
                }
            )
        }

    }
}