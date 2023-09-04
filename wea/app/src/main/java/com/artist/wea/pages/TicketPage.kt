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
import com.artist.wea.constants.DummyValues

@Composable
fun TicketPage(
    navController: NavHostController,
    ticketId:String
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
                // TODO... 서버와의 통신으로 티켓정보 받아오는 로직 구현
                DummyValues().ticketInfoList[ticketId]?.let {
                    Ticket(
                        ticketInfo = it
                    )
                }
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


