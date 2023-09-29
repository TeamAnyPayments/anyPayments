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
import androidx.compose.ui.res.stringResource
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
import com.artist.wea.data.ArtistInfo
import com.artist.wea.data.UserProfile

@Composable
fun ArtistQuitPage(
    navController: NavHostController
) {

    // TODO... prefs를 통해서 아티스트 데이터를 추출해서 렌더링 하도록 설계
    val artistInfo = ArtistInfo(
        profileImgURL = "https://image.kmib.co.kr/online_image/2014/1015/201410152053_61170008765071_1.jpg",
        bgImgURL = "https://img.hankyung.com/photo/202205/01.29843403.1.jpg",
        artistName = "ENJOY",
        comment = "안녕하세요, 행복을 노래하는 가수입니다.",
        mainIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                "\n" +
                "홍대 스트릿 버스커 그룹 로드 버스킹입니다.\n" +
                "\n" +
                "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                "\n" +
                "음악을 사랑한다는 마음 하나라면,\n" +
                "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                "\n" +
                "창립일\n" +
                "2013. 01. 16.\n" +
                "\n" +
                "자주 출몰하는 장소\n" +
                "홍대입구 2번 출구, 강남역 3번 출구\n" +
                "\n" +
                "인스타그램\n" +
                "@abc_123_heart",
    )

    val userProfile = remember { mutableStateOf(UserProfile(
        userId = "test0001",
        email = "test1234@test.com"
    )) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.mono50)))
    {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_artist_quit)
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
                        text = "아티스트 탈퇴 전에 아래의 유의사항을 꼭 확인해주세요.",
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
                        text = "아래의 아티스트 정보에 대한 탈퇴 처리가 접수됩니다.",
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
                            text = "${artistInfo.artistName}\n" +
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
                            text = "· 아티스트를 탈퇴하실 경우 기존의 아티스트 정보에 대해 다시 접근하실 수 없으며, 보유하신 현금성 및 디지털 자산은 소멸되어 복구가 불가능합니다.",
                            style = get14TextStyle()
                        )
                        Text(
                            text = "· 아직 정산되지 않은 공연 수익이 있다면, 반드시 탈퇴 전 정산 후 탈퇴를 진행해주시기 바랍니다.",
                            style = get14TextStyle()
                        )
                        Text(
                            text = "· 탈퇴는 신청 접수 후 평균 1주일 이내에 처리되며, 탈퇴 처리에 대한 내용은 상기 이메일 주소로 전송됩니다.",
                            style = get14TextStyle()
                        )
                    }

                    // 탈퇴 사유 리스트
                    val selectOptions = listOf("사용빈도 낮음", "서비스 불만", "고객응대 불만", "아티스트 정보 초기화", "기타")

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
                    text = "위의 유의사항을 모두 확인하였으며, 아티스트 탈퇴시 아티스트 정보의 소멸에 동의합니다.",
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
