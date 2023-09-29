package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TicketItem
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get12TextStyle

// 티켓 정보들 리스트로 볼 페이지
@Composable
fun TicketListPage(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_ticket_list)
        )
        // banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colorResource(id = R.color.mono200))
        ) {
            Text(
                text= stringResource(R.string.text_guide_ticket_list),
                style = get12TextStyle()
                    .copy(
                        color = colorResource(id = R.color.mono700)
                    ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp, 4.dp)

            )
        }

        val ticketInfoList = DummyValues().ticketInfoList.values

        ticketInfoList.forEach{ ticketInfo ->
            TicketItem(
                ticketInfo = ticketInfo,
                modifier = Modifier.clickable {
                    if(ticketInfo.isOnAir){ // TODO.. 나중에 완성되면 지우기!
                        navController.navigate(PageRoutes.Ticket.route+"/${ticketInfo.serialNo}")
                    }
                }
            )
        }

    }
}
