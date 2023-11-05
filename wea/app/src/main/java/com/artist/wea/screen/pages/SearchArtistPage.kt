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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues
import com.artist.wea.data.ArtistInfo
import com.artist.wea.screen.components.ArtistInfoItem
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.ReplaceImage
import com.artist.wea.screen.components.SearchBar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// 아티스트 검색 페이지
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

        var artistInfoList = DummyValues.artistSearchList.values
        val popList = artistInfoList.sortedByDescending { it.userId }
        val nameList = artistInfoList.sortedBy { it.artistName }
        val dateList = artistInfoList.sortedBy { it.location }

        val currentList = remember { mutableStateOf<Collection<ArtistInfo>>(artistInfoList) }

        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_artist_search)
        )
        
        // 검색...
        val searchText = remember { mutableStateOf<String>("") }
        val searchList = remember { mutableStateOf<Collection<ArtistInfo>>(listOf()) }
        // debouce 변수
        // 더블클릭 방지를 위한 Job 변수
        var debounceJob: Job? = null

        searchText.value = SearchBar(
            searchOptions = arrayOf("선택안함","인기순","이름순","등록일순"),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            sortAction = arrayOf(
                { currentList.value = artistInfoList},
                { currentList.value = popList },
                { currentList.value = nameList },
                { currentList.value = dateList }
            ),
            searchAction = {
                if(searchText.value.isNotEmpty()){
                    debounceJob?.cancel()
                    debounceJob = GlobalScope.launch {
                        delay(300)
                        val filteredList = currentList.value.filter { artistInfo ->
                            artistInfo.artistName.contains(searchText.value, ignoreCase = true)
                        }
                        searchList.value = filteredList
                    }
                }
            }
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

            if(!searchText.value.isEmpty() && searchList.value.isEmpty()){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp)){
                    ReplaceImage(
                        imageModel = R.drawable.icon_no_search,
                        modifier = Modifier
                            .width(256.dp)
                            .height(128.dp)
                            .align(Alignment.Center)
                    )
                }
            } else if(searchList.value.isEmpty()){
                currentList.value.forEach{ artistInfo ->
                    ArtistInfoItem(
                        navController = navController,
                        artistInfo = artistInfo,
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                    )
                }
            }else {
                searchList.value.forEach{ artistInfo ->
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
}