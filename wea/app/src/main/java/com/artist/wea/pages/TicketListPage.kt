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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TicketItem
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.data.TicketInfo
import java.time.LocalDateTime

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
            pageTitle = "티켓 후원 내역"
        )
        // banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(colorResource(id = R.color.mono200))
        ) {
            Text(
                text="회원님이 후원 하신 공연에 대한 정보를 조회합니다.",
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

        TicketItem(ticketInfo =
            TicketInfo(
                serialNo = "1234-5678-1234",
                concertName = "무슨무슨 버스킹",
                teamName = "버스킹과 아이들",
                dateTime = LocalDateTime.now(),
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
                isOnAir = true,
            ),
            modifier = Modifier.clickable {
                navController.navigate(PageRoutes.Ticket.route)
            }
        )
        TicketItem(ticketInfo =
            TicketInfo(
                serialNo = "1234-5678-1234",
                concertName = "무슨무슨 버스킹",
                teamName = "버스킹과 아이들",
                dateTime = LocalDateTime.now(),
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
            )
        )
        TicketItem(ticketInfo =
            TicketInfo(
                serialNo = "1234-5678-1234",
                concertName = "무슨무슨 버스킹",
                teamName = "버스킹과 아이들",
                dateTime = LocalDateTime.now(),
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
            )
        )
        TicketItem(ticketInfo =
            TicketInfo(
                serialNo = "1234-5678-1234",
                concertName = "무슨무슨 버스킹",
                teamName = "버스킹과 아이들",
                dateTime = LocalDateTime.now(),
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞",
            )
        )
    }
}
