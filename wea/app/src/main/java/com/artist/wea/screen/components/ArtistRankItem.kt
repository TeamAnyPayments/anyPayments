package com.artist.wea.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.data.ArtistRankData
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getCardDefColors
import com.artist.wea.constants.getCardDefElevation

// 아티스트 순위 정보 조회 시 화면 렌더링에 사용하는 컨테이너 성 컴포저블
@Composable
fun ArtistRankItem(
    navController: NavHostController, // 하위 컴포저블에 네비게이션 컨트롤러를 전달하기 위해 파라미터로 받음
    modifier: Modifier = Modifier,
    artistRankData: ArtistRankData = // 아티스트 정보를 담은 담은 데이터 클래스
        ArtistRankData(
            no = -1,
            artistName = "",
            artistAddress = "",
            artistImgURL = ""
        )
){
    // Card 컴포저블 사용하여 UI를 구성
    Card(
        modifier= modifier.clickable {
            navController.navigate(PageRoutes.ArtistInfo.route)
        },
        colors = getCardDefColors(),
        elevation = getCardDefElevation(dpSize = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
            ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            // artist-info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(10f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                // 아이콘
                WeaIconImage(
                    imgUrl = artistRankData.artistImgURL,
                    size = 64.dp,
                    isClip = true
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    // 아티스트 명
                    Text(
                        text = artistRankData.artistName,
                        style = get14TextStyle()
                    )
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Icon(
                           Icons.Filled.LocationOn,
                            contentDescription = "주소",
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.red400)
                        )
                        // 아티스트 활동 주소!
                        Text(
                            text = artistRankData.artistAddress,
                            style = get14TextStyle()
                        )
                    }
                }
            }
            // 아티스트 순위!
            Text(
                text = artistRankData.no.toString(),
                style = get14TextStyle(),
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
        }
    }
}
