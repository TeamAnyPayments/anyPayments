package com.artist.wea.screen.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.GlobalState.Companion.bookMarkedArtist
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.screen.components.ArtistInfoItem
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.ReplaceImage

// 아티스트 찜목록 페이지
@Composable
fun MyArtistPage(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTopBar (
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_my_artist)
        )
        val userName:String = "홍길동" // TODO prefs로부터 사용자 이름 불러오기

        // 아티스트 정보
        val artistInfoList = bookMarkedArtist;


        // list..
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            val bookmarkGuideText = "$userName ${stringResource(R.string.text_book_mark_guide_tail)}"
            // 배너
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(color = colorResource(id = R.color.sky_blue300))
            ){
                Text(
                    text = bookmarkGuideText,
                    style = get14TextStyle()
                        .copy(
                            color = colorResource(id = R.color.white)
                        ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            // TODO.. 북마크한 아티스트들 공백 여부 추가
            if (bookMarkedArtist.isEmpty()){

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp)
                ){
                    ReplaceImage(
                        imageModel = R.drawable.icon_no_bookmark,
                        modifier = Modifier
                            .width(256.dp)
                            .height(128.dp)
                            .align(Alignment.Center)
                    )
                }


            }else {
                // 찜 아티스트
                artistInfoList.values.forEach{ artistInfo ->
                    ArtistInfoItem(
                        navController = navController,
                        artistInfo = artistInfo,
                        modifier = Modifier
                            .padding(16.dp, 8.dp),
                        hasLine = false
                    )
                }

            }
        }
    }

}