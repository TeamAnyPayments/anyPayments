package com.artist.wea.screen.pages

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.GlobalState
import com.artist.wea.constants.GlobalState.Companion.currentArtistInfo
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ArtistInfo
import com.artist.wea.screen.components.ArtistInfoItem
import com.artist.wea.screen.components.ConcertSearchItem
import com.artist.wea.screen.components.InfoUnit
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.ShowProfileDialog
import com.artist.wea.screen.components.WeaIconImage
import com.artist.wea.screen.components.WeaWideImage
import com.artist.wea.util.ToastManager

@Composable
fun ArtistInfoPage(
    navController: NavHostController,
){

    // 라우팅 해서 들어온 아티스트 조회 정보들..
    // 아티스트 아이디
    val argument = navController?.currentBackStackEntry?.arguments;
    val id =  argument?.getString("userId").toString()?:"none"
    val artistInfoLog = DummyValues.artistSearchList[id]?: ArtistInfo()

    // temp
    val joinArtist = GlobalState.joinedArtistInfo.value
    val flag = joinArtist.artistName.isEmpty() || id != joinArtist.userId;
    val artistInfo = if(flag)  artistInfoLog else joinArtist
    currentArtistInfo.value = artistInfo

    // TODO 현재 사용자가 아티스트 프로필을 수정할 권한이 있는지 판단할 boolean 변수
    val isEditable = joinArtist.userId == id;


    var isBookMarked = remember {
        mutableStateOf(GlobalState.bookMarkedArtist[currentArtistInfo.value.userId] != null)
    }
    // 토스트 메세지를 위한 context
    val context = LocalContext.current;

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
                defImgID = R.drawable.bg_def_artist,
                height = 164.dp,
                bitmap = artistInfo.bgBitmap
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
                bitmap = artistInfo.profileBitmap
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
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                if (!isBookMarked.value) {
                                    GlobalState
                                        .bookMarkedArtist
                                        .putIfAbsent(artistInfo.userId, artistInfo)
                                    isBookMarked.value = true;
                                    ToastManager.shortToast(context, "북마크에 추가되었습니다.")
                                } else {
                                    GlobalState
                                        .bookMarkedArtist
                                        .remove(artistInfo.userId)
                                    isBookMarked.value = false;
                                    ToastManager.shortToast(context, "북마크를 취소하였습니다.")
                                }
                            },
                        // 북마크 조건부 분기 처리
                        tint = if(isBookMarked.value)
                            colorResource(id = R.color.kakao_yellow)
                            else colorResource(id = R.color.mono100)
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
        val artistInfoList = DummyValues.memberList

        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "Member",
            screen = {
                artistInfoList.forEach { artistInfo ->
                    ArtistInfoItem(
                        navController = navController,
                        artistInfo = artistInfo,
                        hasLine = false
                    )
                }
            },
        )

        // 공연 이력
        DummyValues.name = artistInfo.artistName
        DummyValues.url = artistInfo.profileImgURL
        val concert = DummyValues.concertLogList[0]
        concert.artistName = artistInfo.artistName

        // History
        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "History",
            screen = {
                repeat(6){
                    ConcertSearchItem(
                        navController = navController,
                        content = concert,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        isActive = concert.concertId == DummyValues.defConcertInfo.concertId // TODO 지우기 .. temp..
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