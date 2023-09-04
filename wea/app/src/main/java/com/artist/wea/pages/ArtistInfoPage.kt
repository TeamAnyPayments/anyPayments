package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ArtistInfoItem
import com.artist.wea.components.ConcertSearchItem
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.ShowProfileDialog
import com.artist.wea.components.WeaIconImage
import com.artist.wea.components.WeaWideImage
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ArtistInfoPage(
    navController: NavHostController,
    id:Int = -1
){
    // TODO 현재 사용자가 아티스트 프로필을 수정할 권한이 있는지 판단할 boolean 변수
    val isEditable = true;

    // TODO navController를 통해서 아티스트 데이터를 추출해서 렌더링 하도록 설계
    val artistInfo = DummyValues().aritstSearchList[id-1]

    val modalVisibleState = remember { mutableStateOf(false) }
    ShowProfileDialog(
        visible = modalVisibleState.value,
        defaultImageURL = artistInfo.profileImgURL,
        localImgBitmap = null,
        onDismissRequest = {
            modalVisibleState.value = false
        })

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50))
            .verticalScroll(scrollState)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(328.dp)
                .background(color = colorResource(id = R.color.mono50))
        ){
            // 상단 바

            if(!isEditable){ // 일반사용자?
                PageTopBar(
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .zIndex(2f),
                    hasTransparency = true)

            }else { // 아티스트 본인?
                PageTopBar(
                    navController = navController,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .zIndex(2f),
                    hasTransparency = true,
                    rightMenuText = "수정",
                    rightMenuAction = { navController.navigate(PageRoutes.ArtistInfoModify.route)},
                    rightMenuTextStyle = get14TextStyle()
                        .copy(color = colorResource(id = R.color.white)),
                )
            }

            WeaWideImage(
                modifier = Modifier.align(Alignment.TopStart),
                imgUrl = artistInfo.bgImgURL,
                height = 164.dp
            )

            // 아티스트 프로필
            WeaIconImage(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable {
                       modalVisibleState.value = true;
                    },
                imgUrl = artistInfo.profileImgURL,
                size = 144.dp,
                isClip = true,
            )

            // 아티스트 정보 layer
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp)
                    .zIndex(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 아티스트 이름
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = artistInfo.artistName,
                        style = getDefTextStyle(),
                    )
                    Icon(
                        Icons.Filled.Star, 
                        contentDescription = "북마크",
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(id = R.color.kakao_yellow) // TODO 북마크 조건부 분기 처리
                    )
                }
                // 아티스트 한줄 소개
                Text(
                    text = artistInfo.comment,
                    style = get14TextStyle().copy(
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
        }

        // 프로필 소개
        InfoUnit(
           modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "프로필 소개",
            screen = {
                Text(
                    text = artistInfo.mainIntroduce,
                    style = getDefTextStyle()
                )
            },
        )

        // 멤버 소개
        val artistInfoList = DummyValues().memberList

        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "Member",
            screen = {
                artistInfoList.forEachIndexed { index, artistInfo ->
                    ArtistInfoItem(
                        navController = navController,
                        artistInfo = artistInfo,
                        hasLine = false
                    )
                }
            },
        )

        // 공연 이력
        val concertList = DummyValues().concertLogList

        // History
        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "History",
            screen = {
                concertList.forEach {
                        item ->
                    ConcertSearchItem(
                        navController = navController,
                        content = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            //.background(color = colorResource(id = R.color.mono50))
                    )
                }
            },
            screenModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        
        Spacer(modifier = Modifier.height(64.dp))

    }

}