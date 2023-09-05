package com.artist.wea.pages

import android.graphics.Bitmap
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.AddressForm
import com.artist.wea.components.NaverMapComponentWithMapView
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleCapsuleSearchForm
import com.artist.wea.components.TitleDropdownForm
import com.artist.wea.components.TitleImageForm
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.TitleStartEndTimeForm
import com.artist.wea.constants.getButtonColor
import com.artist.wea.constants.getDefTextStyle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import java.time.LocalDateTime

@Composable
fun OpenConcertPage(
    navController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxSize()
){
    val context = LocalContext.current

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
            var titleImage by remember { mutableStateOf<Bitmap?>(null) }
            var description by remember { mutableStateOf("") }
            var genre = remember { mutableStateListOf<String>() }
            var concertStartTime by remember { mutableStateOf<LocalDateTime?>(null) }
            var concertEndTime by remember { mutableStateOf<LocalDateTime?>(null) }
            var concertLocation by remember { mutableStateOf("") }
            var detailLocation by  remember { mutableStateOf("") }
            var ticketPrice by remember { mutableStateOf("") }
            var tag = remember { mutableStateListOf<String>() }

            AddressForm()

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

            description = TitleInputForm(
                titleText = "공연 소개",
                hintText = "공연 소개를 입력하세요."
            )

            genre = TitleCapsuleSearchForm(
                titleText = "장르",
                hintText = "장르 검색",
                searchList = arrayOf("인디 록", "인디 음악", "인디 팝", "인디 포크", "재즈", "재즈 랩", "재즈 록", "재즈 팝"),
                capacity = 3
            )

            val (startDateTime, endDateTime) = TitleStartEndTimeForm(
                titleText = "공연 시간",
                startTitle = "공연 시작 예정 시간",
                endTitle = "공연 종료 예정 시간"
            )

            concertStartTime = startDateTime
            concertEndTime = endDateTime

            val mapView = NaverMapComponentWithMapView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            mapView.getMapAsync {
                it.setOnMapLongClickListener { pointF, latLng ->
                    val marker = Marker().apply {
                        position = LatLng(latLng.latitude, latLng.longitude)
                        map = it
                        setOnClickListener { o ->
                            o.map = null
                            true
                        }
                    }

                }
            }

            concertLocation = TitleInputForm(
                titleText = "공연 장소",
                hintText = "공연 장소를 입력하세요."
            )

            detailLocation = TitleInputForm(
                titleText = "상세 주소",
                hintText = "상세 주소를 입력하세요."
            )

            ticketPrice = TitleInputForm(
                titleText = "최소 후원금",
                hintText = "단위 : 원",
                isNumber = true
            )

            tag = TitleCapsuleSearchForm(
                titleText = "태그",
                hintText = "태그 검색",
                searchList = arrayOf(),
                capacity = 5,
                isTagCapsule = true
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    Toast.makeText(context, "제출", Toast.LENGTH_SHORT).show()
                },
                colors = getButtonColor()
            ) {
                Text(
                    text = "공연 개설하기",
                    style = getDefTextStyle(),
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}