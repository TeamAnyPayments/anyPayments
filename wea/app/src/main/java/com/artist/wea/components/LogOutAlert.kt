package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle


// 로그아웃 시 경고창, LogOutAlert
@Composable
fun LogOutAlert(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    logOutAction: () -> Unit
) {
    if (visible) {
        CustomAlertDialog(onDismissRequest = { onDismissRequest() }) {
            // CUSTOM VIEW...
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(156.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = colorResource(id = R.color.mono50)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // header
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.text_logout_guide),
                        style = getDefTextStyle(),
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // 취소 버튼
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .weight(1f)
                            .background(colorResource(id = R.color.mono300))
                            .clickable {
                                onDismissRequest()
                            }
                    ){
                        Text(
                           text = "취소",
                            style = get14TextStyle()
                                .copy(color = colorResource(id = R.color.white)),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    // 로그아웃 버튼
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .weight(1f)
                            .background(colorResource(id = R.color.dark_orange300))
                            .clickable {
                                logOutAction()
                            }
                    ){
                        Text(
                            text = "로그아웃",
                            style = get14TextStyle()
                                .copy(color = colorResource(id = R.color.white)),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }

            }
        }
    }
}