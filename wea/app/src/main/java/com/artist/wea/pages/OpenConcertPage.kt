package com.artist.wea.pages

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.components.ImageSelectBox
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleDropdownForm
import com.artist.wea.components.TitleImageForm
import com.artist.wea.components.TitleInputForm

@Composable
fun OpenConcertPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
){
    Column(
        modifier = modifier
    ) {
        PageTopBar (
            navController = navController,
            pageTitle = "공연 개설하기"
        )

        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(16.dp, 12.dp)
                .verticalScroll(scrollState)
        ) {
            var artist by remember { mutableStateOf("") }
            var title by remember { mutableStateOf("") }
            var titleImage:Bitmap? by remember { mutableStateOf(null) }
            var ticket by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var genre by remember { mutableStateOf("") }
            var concertStartTime by remember { mutableStateOf("") }
            var concertEndTime by remember { mutableStateOf("") }
            var concertLocation by remember { mutableStateOf("") }
            var detailLocation by  remember { mutableStateOf("") }
            var ticketPrice by remember { mutableStateOf("") }
            var tag by remember { mutableStateOf("") }

            artist = TitleDropdownForm(
                listItems = arrayOf("소속1", "소속2", "소속3"),
                innerLabel = "내 소속",
                titleText = "아티스트"
            )
            title = TitleInputForm(
                titleText = "타이틀",
                hintText = "타이틀을 입력하세요."
            )
            titleImage = TitleImageForm(
                titleText = "타이틀 이미지"
            )
//            ticket = TitleInputForm(
//                titleText = "티켓 이미지",
//                hintText = "티켓 이미지를 선택하세요."
//            )
//            description = TitleInputForm(
//                titleText = "공연 소개",
//                hintText = "공연 소개를 입력하세요."
//            )
//            genre = TitleInputForm(
//                titleText = "장르",
//                hintText = "장르 입력"
//            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                concertStartTime = TitleInputForm(
//                    titleText = "시작 예정 시간",
//                    hintText = "시작 예정 시간을 입력하세요."
//                )
//                concertEndTime = TitleInputForm(
//                    titleText = "종료 예정 시간",
//                    hintText = "종료 예정시간을 입력하세요."
//                )
//            }
//
//            concertLocation = TitleInputForm(
//                titleText = "공연 장소",
//                hintText = "공연 장소를 입력하세요."
//            )
//
//            detailLocation = TitleInputForm(
//                titleText = "상세 주소",
//                hintText = "상세 주소를 입력하세요."
//            )
//
//            ticketPrice = TitleInputForm(
//                titleText = "최소 후원금",
//                hintText = "단위 : 원"
//            )
//
//            tag = TitleInputForm(
//                titleText = "태그",
//                hintText = "태그 입력"
//            )

        }
    }
}