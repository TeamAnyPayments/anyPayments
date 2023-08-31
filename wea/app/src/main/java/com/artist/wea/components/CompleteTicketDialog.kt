package com.artist.wea.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun CompleteTicketDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
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
                   text =  "티켓 발급 완료",
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
                    text = "후원이 정상적으로 완료되었어요. \uD83C\uDF89\n" +
                            "이번 공연에 처음으로 후원해주셨군요 \uD83D\uDC40\n" +
                            "회원님이 후원한 아티스트와 함께할 특별한 순간을 위해 티켓이 발급되었어요. \uD83D\uDE0D\n",
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
                    text = "*본 메세지는 첫 후원 시 1회만 노출됩니다.",
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
                    text = "발급된 티켓은 메인 화면 > TICKETS 메뉴를 통해 확인하실 수 있습니다.",
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
