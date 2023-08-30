package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.CheckBoxRow
import com.artist.wea.components.MultipleRadioButtons
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.UserProfile

@Composable
fun UserQuitPage(
    navController: NavHostController
) {
    // TODO prefs로부터 저장된 json 사용자 정보 불러와 렌더링하기
    val userProfile = remember { mutableStateOf(
        UserProfile(
            name = "홍길동",
            userId = "test0001",
            email = "test1234@test.com"
            ))}

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.mono50)))
    {
        PageTopBar(
            navController = navController,
            pageTitle = "회원 탈퇴"
        )

        Box(modifier = Modifier
            .fillMaxSize()
        ){
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .align(Alignment.TopStart)
            ) {

                // 유의사항 안내
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(colorResource(id = R.color.mono100))
                ) {
                    Text(
                        text = "탈퇴 시 유의사항 안내",
                        style = getDefTextStyle().copy(
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    )
                }

                // 유의사항 체크
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(colorResource(id = R.color.pastel_yellow100)),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "회원탈퇴를 신청하기 전에 아래의 유의사항을 확인해주세요.",
                        style = get14TextStyle()
                            .copy(
                                color = colorResource(id = R.color.mono600)
                            ),
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 12.dp)
                    )
                    Divider(
                        thickness = 1.dp,
                        color = colorResource(id = R.color.mono300)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp, 12.dp),
                    verticalArrangement = Arrangement.spacedBy(28.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = "아래의 회원정보에 대한 탈퇴 처리가 접수됩니다.",
                        style = get14TextStyle().copy(
                            color = colorResource(id = R.color.red400),
                            fontWeight = FontWeight.Bold
                        )
                    )

                    // 아티스트 및 사용자 정보
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Divider(
                            thickness = 1.dp,
                            color = colorResource(id = R.color.mono300)
                        )
                        Text(
                            text = "${userProfile.value.name}\n" +
                                    "${userProfile.value.userId}\n" +
                                    "${userProfile.value.email}",
                            style = get14TextStyle(),
                            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp)                        )
                        Divider(
                            thickness = 1.dp,
                            color = colorResource(id = R.color.mono300)
                        )
                    }
                    // 안내 문구
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "개인정보 삭제 안내",
                            style = getDefTextStyle()
                                .copy(
                                    fontWeight = FontWeight.Bold
                                )
                        )
                        Text(
                            text = "· 탈퇴 후에는 이메일, 아이디 정보로 다시 로그인할 수 없으며, 보유하신 현금성 및 디지털 자산은 모두 소멸되어 복구가 불가능합니다.",
                            style = get14TextStyle()
                        )
                        Text(
                            text = "· 탈퇴는 신청 접수 후 평균 1주일 이내에 처리되며, 탈퇴 처리에 대한 내용은 상기 이메일 주소로 전송됩니다.",
                            style = get14TextStyle()
                        )
                    }

                    // 탈퇴 사유 리스트
                    val selectOptions = listOf("사용빈도 낮음", "서비스 불만", "고객응대 불만", "개인정보 유출 우려", "기타")

                    val quitReason = MultipleRadioButtons(
                        selectOptions = selectOptions
                    ) // 탈퇴사유 받음

                    Spacer(modifier = Modifier.height(96.dp))
                }
            }

            // bottom Button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(colorResource(id = R.color.mono50))
                    .zIndex(2f)
                    .align(Alignment.BottomEnd),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Divider(
                    thickness = 1.dp,
                    color = colorResource(id = R.color.mono300)
                )
                val isAgreement = remember { mutableStateOf(false) }
                CheckBoxRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 4.dp, end = 4.dp),
                    text = "위의 유의사항을 모두 확인하였으며, 회원 탈퇴 시 보유하신 현금성 및 디지털 자산에 대한 소멸에 동의합니다.",
                    value = isAgreement.value,
                    onClick = {
                        isAgreement.value = !isAgreement.value
                    },
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(1f)
                            .background(color = colorResource(id = R.color.red300)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text =  "탈퇴 신청",
                            style = get14TextStyle()
                                .copy(colorResource(id = R.color.white)),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(1f)
                            .background(color = colorResource(id = R.color.mono300)),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text =  "취소",
                            style = get14TextStyle()
                                .copy(colorResource(id = R.color.white)),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}