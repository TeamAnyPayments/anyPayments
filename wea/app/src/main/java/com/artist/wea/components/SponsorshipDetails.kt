package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.data.SponsorshipLog
import com.artist.wea.util.LDTParser
import java.time.LocalDateTime
import java.util.Locale

@Composable
fun SponsorshipDetails(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
){

    // 목업용 데이터
    val range =  10..30// temp
    var totalMoney = 0;
    val tempDate = LocalDateTime.now()
    val sponsorshipLogList = mutableListOf<SponsorshipLog>()

    for(i in 1..8){
        val account = range.random()*100
        totalMoney+=account
        sponsorshipLogList.add(
            SponsorshipLog(
                date = tempDate,
                account = account
            )
        )
    }

    // outer columns
    Column(modifier = modifier
        .clip(shape = RoundedCornerShape(8.dp))
        .background(color = colorResource(id = R.color.mono50))
        .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // 결제 내역
            Text(
                text = "결제금액",
                style = get14TextStyle()
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                // 총 결제 금액
                Text(
                    text="총 결제 금액",
                    style = get14TextStyle()
                )
                // 결제 총액
                Text(
                    text="${totalMoney} 원",
                    style = get14TextStyle()
                )
            }
        }

        // 상세 후원 내역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {

            // 결제상세
            Text(
                text = "결제상세",
                style = get14TextStyle()
            )

            for(data in sponsorshipLogList){
                // 결제 리스트들
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){

                    // 일자 파싱
                    val parser = LDTParser()
                    val dateString = parser.parseAsStandardString(
                        localDateTime = data.date,
                        standard = "yyyy.MM.dd (E) hh:mm",
                        localeType = Locale.KOREA
                    )

                    // 총 결제 금액
                    Text(
                        text= dateString,
                        style = get14TextStyle()
                    )
                    // 결제 총액
                    Text(
                        text="${data.account} 원",
                        style = get14TextStyle()
                    )
                }
            }
        }

    }

}