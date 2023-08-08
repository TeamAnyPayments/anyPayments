package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ArtistInfoItem
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.SearchBar
import com.artist.wea.constants.PageRoutes
import com.artist.wea.data.ArtistInfo

@Composable
fun SearchArtistPage(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val artistInfoList = arrayOf<ArtistInfo>(
            ArtistInfo(
                "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                artistName = "블라블라 아티스트",
                comment = "좋은 음악 함께 공유해요"
            ),
            ArtistInfo(
                "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                artistName = "무슨무슨 아티스트",
                comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
            ),
            ArtistInfo(
                "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
                artistName = "쏼라쏼라 아티스트",
                comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
            ),
            ArtistInfo(
                "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                artistName = "블라블라 아티스트",
                comment = "좋은 음악 함께 공유해요"
            ),
            ArtistInfo(
                "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                artistName = "무슨무슨 아티스트",
                comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
            ),
            ArtistInfo(
                "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
                artistName = "쏼라쏼라 아티스트",
                comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
            ),
            ArtistInfo(
                "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                artistName = "블라블라 아티스트",
                comment = "좋은 음악 함께 공유해요"
            ),
            ArtistInfo(
                "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                artistName = "무슨무슨 아티스트",
                comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
            ),
            ArtistInfo(
                "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
                artistName = "쏼라쏼라 아티스트",
                comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
            )
        )

        PageTopBar(
            navController = navController,
            pageTitle = "아티스트 검색"
        )
        SearchBar(
            searchOptions = arrayOf("선택안함","인기순","이름순","등록일순"),
            modifier =  Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
        )

        // list..
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            artistInfoList.forEachIndexed{index, artistInfo ->
                ArtistInfoItem(
                    artistInfo = artistInfo,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                        .clickable { navController.navigate(PageRoutes.ArtistInfo.route) }
                )
            }
        }
    }
}