package com.artist.wea.screen.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

// 공연 수익 페이지
@Composable
fun ConcertBenefitPage(
    navController: NavHostController
){
    // TODO... 결제 기능 완성 시 토스 api를 사용하여 결제 금액 가져오기
    val artistName = "무슨무슨 아티스트"
    val benefitCount = 1950000

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_concert_benefit)
        )
        Text(
            text = "$artistName 님의",
            style = getDefTextStyle()
                .copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
        )
        Text(
            text = "총 공연 수익",
            style = getDefTextStyle()
                .copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "지금까지 WE:A 에서 창출하신 수익은",
            style = getDefTextStyle()
                .copy(
                    color = colorResource(id = R.color.mono800)
                )
        )

        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "$benefitCount",
                    style = getDefTextStyle()
                        .copy(
                            fontSize = 32.sp, fontWeight = FontWeight.Bold
                        )
                )
                Text(
                    text = "원",
                    style = get14TextStyle()
                        .copy(
                            color = colorResource(id = R.color.mono600)
                        )
                )
            }
            Text(
                text = "이네요",
                style = get14TextStyle()
                    .copy(
                        color = colorResource(id = R.color.mono600),
                        textAlign = TextAlign.End
                    ),
                // modifier = Modifier.fillMaxWidth(),
            )
        }
        Text(
            text = "앞으로도 WE:A는 아티스트님의 꿈을 응원할게요!",
            style = getDefTextStyle(colorResource(id = R.color.mono600))
        )
        Text(
            text = "WE:A 개발자 일동 드림",
            style = getDefTextStyle(colorResource(id = R.color.mono600))
        )
        
        Text(
            text = stringResource(R.string.text_concert_benefit_detail),
            style = get12TextStyle()
                .copy(color = colorResource(id = R.color.mono600)),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        )

    }

}