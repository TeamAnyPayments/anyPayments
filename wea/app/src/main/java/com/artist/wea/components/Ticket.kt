package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.getDefCardColors
import com.artist.wea.constants.getDefCardElevation
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.TicketInfo

@Composable
fun Ticket(
    modifier: Modifier = Modifier,
    ticketInfo: TicketInfo,
){

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = getDefCardColors(),
        elevation = getDefCardElevation(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    Text(
                        text = "후원 번호",
                        style = get12TextStyle()
                    )
                    Text(
                        text = ticketInfo.serialNo,
                        style = get12TextStyle()
                    )
                }
                // 공유 버튼?
                Icon(
                    Icons.Filled.Share,
                    contentDescription = "공유",
                    modifier = modifier
                        .size(24.dp)
                        .background(colorResource(id = R.color.color_transparency)),
                    tint = colorResource(id = R.color.mono300)
                )
            }

            // image
            Box(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){
                // 무한 스크롤 배너
                InfiniteLoopPager(
                    modifier = Modifier.align(Alignment.Center),
                    list = ticketInfo.concertImgList,
                    height = 196.dp,
                    posIdx = 2
                )
                // 공연 정보
                Column(
                    Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = ticketInfo.concertName,
                        style = getDefTextStyle()
                            .copy(
                                fontSize = 24.sp,
                                color = colorResource(id = R.color.white)
                            )
                    )
                    Text(
                        text = ticketInfo.teamName,
                        style = getDefTextStyle()
                            .copy(
                                color = colorResource(id = R.color.white
                                )
                            )
                    )
                }
            }

            // 공연 정보
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.Start
            ) {

                val dateString = "8/21"
                val dayName = "(월)"
                val timeString = "09:00 ~ 11:00"
                val tempString = 365.toString()

                // 공연 일자
                TicketInfoColFrame(
                    title = "공연일",
                    content = {
                        Row(modifier= Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                            Text(
                                text = dateString,
                                style = getDefTextStyle()
                                    .copy(
                                        fontSize = 32.sp
                                    )
                            )
                            Text(
                                text = dayName,
                                style = getDefTextStyle()
                            )
                        }
                    }
                )

                // 공연 시간
                TicketInfoColFrame(
                    title = "공연 시간",
                    content = {
                        Text(
                            text = timeString,
                            style = getDefTextStyle()
                                .copy(
                                    fontSize = 32.sp
                                )
                        )
                    }
                )
                // 공연 장소
                TicketInfoColFrame(
                    title = "공연 장소",
                    content = {
                        Text(
                            text = ticketInfo.location,
                            style = getDefTextStyle()
                        )
                    }
                )

                // 공연 온도
                TicketInfoRowFrame(
                    title = "공연 온도",
                    content = {
                        Row(modifier= Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                            Text(
                                text = tempString,
                                style = getDefTextStyle()
                                    .copy(
                                        fontSize = 32.sp
                                    )
                            )
                            Text(
                                text = "℃",
                                style = getDefTextStyle()
                            )
                        }
                    }
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        color = colorResource(
                            id = R.color.sky_blue300
                        )
                    )
            )
            // 가이드
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TicketGuideText(
                    text = "실제 공연 시간은 현장 및 기상 상황에 따라 유동적일 수 있으니 이점 참고해 주시기 바랍니다."
                )
                TicketGuideText(
                    text = "우리동네 버스킹 플랫폼 WE:A는 건전한 문화 생활을 응원합니다. 아름다운 공연 문화 정착을 위해 에티켓을 꼭 지켜주세요."
                )
            }

        }
    }

}

@Composable
fun TicketInfoColFrame(
    title:String,
    content:@Composable () -> Unit
){
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            style = get12TextStyle()
                .copy(
                    color = colorResource(id = R.color.mono800)
                )
        )
        content()
    }

}

@Composable
fun TicketInfoRowFrame(
    title:String,
    isIcon:Boolean = true,
    icon: ImageVector = Icons.Filled.Info,
    content:@Composable () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = get12TextStyle()
                    .copy(
                        color = colorResource(id = R.color.mono800)
                    )
            )
            if(isIcon){
                Icon(
                    icon,
                    contentDescription = "icon",
                    modifier = Modifier.size(16.dp),
                    tint = colorResource(id = R.color.mono800)
                )
            }
        }
        content()
    }
}

@Composable
fun TicketGuideText(
    text:String
){
    Text(
        text = text,
        style = get12TextStyle()
            .copy(
                fontSize = 10.sp,
                color = colorResource(id = R.color.mono700),
                textAlign = TextAlign.Left
            ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )

}
