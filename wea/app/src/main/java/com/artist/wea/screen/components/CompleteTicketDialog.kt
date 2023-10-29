package com.artist.wea.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

// 티켓 발급 성공 시 완료를 알려주기 위한 알람창 용 컴포저블
@Composable
fun CompleteTicketDialog(
    visible: Boolean, // Dialog 표시 여부
    onDismissRequest: () -> Unit, // 알람창 닫기 함수 (visible의 상태를 변경할 함수가 주어져야 함)
){
    if (visible) {
        CustomAlertDialog(onDismissRequest = { onDismissRequest() }) {
            // CUSTOM VIEW...
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = colorResource(id = R.color.mono50)),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 티켓 발급 성공 시 표현할 레이아웃들...!
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "닫기",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onDismissRequest()
                            },
                        tint = colorResource(id = R.color.mono600)
                    )
                }
                // 제목
                Text(
                   text = stringResource(R.string.text_complete_create_ticket),
                    style = getDefTextStyle()
                        .copy(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp, end = 16.dp)
                )

                // 티켓 아이콘
                WeaIconImage(
                    defImgID = R.drawable.icon_ticket,
                    size = 144.dp,
                    isClip = false
                )
                Text(
                    text = stringResource(id = R.string.text_complete_ticket_greet),
                    style = get14TextStyle()
                        .copy(
                            textAlign = TextAlign.Center
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp, end = 16.dp)
                )
                Text(
                    text = stringResource(R.string.text_tickt_guide1),
                    style = get12TextStyle()
                        .copy(
                            color = colorResource(id = R.color.mono300),
                            textAlign = TextAlign.Center
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp, end = 16.dp)
                )
                Text(
                    text = stringResource(R.string.text_tickt_guide2),
                    style = get12TextStyle()
                        .copy(
                            color = colorResource(id = R.color.mono300),
                            textAlign = TextAlign.Center
                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 16.dp, end = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
