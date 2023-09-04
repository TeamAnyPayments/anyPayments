package com.artist.wea.pages

import androidx.compose.foundation.background
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
import com.artist.wea.constants.DummyValues

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
        val artistInfoList = DummyValues().aritstSearchList.values

        //  arrayOf<ArtistInfo>

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
            artistInfoList.forEach{ artistInfo ->
                ArtistInfoItem(
                    navController = navController,
                    artistInfo = artistInfo,
                    modifier = Modifier
                        .padding(16.dp, 8.dp)
                )
            }
        }
    }
}