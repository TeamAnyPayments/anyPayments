package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.data.TicketInfo
import com.artist.wea.util.LDTParser
import java.util.Locale

@Composable
fun TicketItem(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    ticketInfo: TicketInfo
){
    Column(
        modifier = modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {

        val badgeText = arrayOf("ON AIR", "END")
        val badgeColor = arrayOf(
            colorResource(id = R.color.red300),
            colorResource(id = R.color.mono300)
        )
        // 일자 파싱
        val parser = LDTParser()
        val dateString = parser.parseAsStandardString(
            localDateTime = ticketInfo.dateTime,
            standard = "yyyy.MM.dd (E)",
            localeType = Locale.KOREA
        )

        // 공연 명과 뱃지
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 공연 명
            Text(
                text = ticketInfo.concertName,
                style = get14TextStyle()
            )
            // 뱃지
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (ticketInfo.isOnAir) badgeColor[0] else badgeColor[1]
                    )
            ){
                Text(
                    text = if(ticketInfo.isOnAir) badgeText[0] else badgeText[1],
                    style = get12TextStyle()
                        .copy(
                            color = colorResource(id = R.color.white)
                        ),
                    modifier = Modifier
                        .padding(8.dp, 4.dp)
                )
            }

        }
        // 아티스트 이름
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = ticketInfo.teamName,
                style = get14TextStyle()
            )

        }
        // 공연 일자
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 공연 일자
            Text(
                text = "공연 일자",
                style = get12TextStyle()
            )
            Text(
                text = dateString, // TODO .. 2023.01.02(월) 로 바꾸기
                style = get12TextStyle()
            )
        }

        // 공연 장소
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "공연 장소",
                style = get12TextStyle()
            )

            Text(
                text = ticketInfo.location,
                style = get12TextStyle()
            )

        }
        // 밑줄
        Divider(
            thickness = 1.dp,
            color = colorResource(id = R.color.mono300)
        )

    }
}
