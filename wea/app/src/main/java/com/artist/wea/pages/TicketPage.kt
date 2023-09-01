package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.SponsorshipDetails
import com.artist.wea.components.Ticket
import com.artist.wea.components.sidemenu.GoogleAdItem
import com.artist.wea.data.TicketInfo
import java.time.LocalDateTime

@Composable
fun TicketPage( navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono100)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = "모바일 티켓"
        )
        // body frame
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp)
            ) {
                Ticket(
                    ticketInfo = TicketInfo(
                        serialNo = "1234-5678-1234",
                        concertName = "무슨무슨 버스킹",
                        teamName = "버스킹과 아이들",
                        concertImgList = listOf(
                            "https://img.hankyung.com/photo/202206/AKR20220622048100051_01_i_P4.jpg",
                            "https://img.khan.co.kr/news/2022/05/13/l_20220513010016444001511313.jpg",
                            "https://img.khan.co.kr/news/2022/05/13/l_2022051301001644400151132.jpg",
                            "https://blog.kakaocdn.net/dn/lvIrz/btrSqGWAmW3/4D1zPsA9vcYOCroUHfKkM0/img.png"),
                        dateTime = LocalDateTime.now(),
                        location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))
                SponsorshipDetails()
                Spacer(modifier = Modifier.height(32.dp))
            }
            // 구글 광고
            GoogleAdItem(
                height = 50.dp
            )
        }
    }
}


