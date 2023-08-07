package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.components.uidtclass.SearchArtistInfo

@Composable
fun ConcertListComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar(
            searchOptions = arrayOf("선택안함", "거리순", "최신순", "인기순")
        )

        val artistList = arrayOf<SearchArtistInfo>(
            SearchArtistInfo(
                imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                artistName = "무슨무슨 아티스트",
                concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
            ),
            SearchArtistInfo(
                imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                artistName = "블라블라 아티스트",
                concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                location = "서울 서대문구 신촌역로 17 GS 25 앞"
            ),
            SearchArtistInfo(
                imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                artistName = "울라울라 아티스트",
                concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
            ),
            SearchArtistInfo(
                imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                artistName = "무슨무슨 아티스트",
                concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
            ),
            SearchArtistInfo(
                imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                artistName = "블라블라 아티스트",
                concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                location = "서울 서대문구 신촌역로 17 GS 25 앞"
            ),
            SearchArtistInfo(
                imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                artistName = "울라울라 아티스트",
                concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
            ),
            SearchArtistInfo(
                imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                artistName = "무슨무슨 아티스트",
                concertIntroduce = "우리는 모두 이번 생은 처음이니까 지금이..",
                location = "경기 고양시 일산서구 경의로 855-13 올리브영 앞"
            ),
            SearchArtistInfo(
                imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                artistName = "블라블라 아티스트",
                concertIntroduce = "Drippin Your Self!, 감성 힙합 공연",
                location = "서울 서대문구 신촌역로 17 GS 25 앞"
            ),
            SearchArtistInfo(
                imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                artistName = "울라울라 아티스트",
                concertIntroduce = "무더운 밤 잠이 오지 않는 그대를 위한 감성 힐링콘...",
                location = "서울 마포구 와우산로21길 19-3 홍익문화공원"
            )
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
            artistList.forEach {
                    item ->
                ConcertSearchItem(
                    content = item
                )
            }
        }
    }
}