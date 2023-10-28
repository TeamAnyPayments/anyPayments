package com.artist.wea.screen.pages

import android.content.Context
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.screen.activity.PaymentsActivity
import com.artist.wea.screen.components.ArtistInfoItem
import com.artist.wea.screen.components.Badge
import com.artist.wea.screen.components.CompleteTicketDialog
import com.artist.wea.screen.components.ConcertDetailTable
import com.artist.wea.screen.components.GuideBubble
import com.artist.wea.screen.components.InfiniteLoopPager
import com.artist.wea.screen.components.InfoUnit
import com.artist.wea.screen.components.LargeButton
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefTextStyle

// 콘서트 정보 페이지
@Composable
fun ConcertInfoPage(
    navController: NavHostController,
    concertId:String
){
    // TODO navController를 통해서 공연 데이터를 추출해서 렌더링 하도록 설계
    val concertInfo =  DummyValues().concertItems[concertId]?:DummyValues().defConcertInfo
    val context = LocalContext.current

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
            pageTitle = concertInfo.concertTitle,
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
                    list = concertInfo.concertImgList,
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
                                text = concertInfo.concertIntroduce,
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
                                text = concertInfo.locations,
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
                    detailInfoMap["장르"] = concertInfo.genre
                    detailInfoMap["시작일"] = concertInfo.startDate.toString()
                    detailInfoMap["종료일"] = concertInfo.endDate.toString()
                    detailInfoMap["공연 시간"] = concertInfo.concertTime.toString()
                    detailInfoMap["최소 후원금$"] = concertInfo.minSupportAccount.toString()+" 원"
                    detailInfoMap["누적 관람객$"] = concertInfo.cumulativeAudience.toString()

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
                        concertInfo.tags.forEach { tagName ->
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
                            concertInfo.memberList.forEach { artistInfo ->
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
                    // modalVisibleState.value = true
                    showPayments(context = context)

                }
            )
        }
    }
}

fun showPayments(context: Context){
    val intent = Intent(context, PaymentsActivity::class.java)
    context.startActivity(intent)
}