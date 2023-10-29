package com.artist.wea.screen.pages

import android.graphics.Bitmap
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
import androidx.compose.runtime.MutableState
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
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.GlobalState
import com.artist.wea.constants.getButtonColor
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ConcertListInfo
import com.artist.wea.data.OpenConcertForm
import com.artist.wea.screen.components.AddressForm
import com.artist.wea.screen.components.NaverMapComponentWithMapView
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.TitleCapsuleSearchForm
import com.artist.wea.screen.components.TitleImageForm
import com.artist.wea.screen.components.TitleInputForm
import com.artist.wea.screen.components.TitleStartEndTimeForm
import com.artist.wea.util.ToastManager
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

            // data object
            val openConcertForm:MutableState<OpenConcertForm> = remember {
                mutableStateOf(OpenConcertForm())
            }

//            artist = TitleDropdownForm(
//                listItems = arrayOf("소속1", "소속2", "소속3"),
//                innerLabel = "내 소속",
//                titleText = "아티스트"
//            )

            title = TitleInputForm(
                titleText = "공연명",
                hintText = "공연 이름을 입력해주세요."
            )
            openConcertForm.value.concertName = title // new

            titleImage = TitleImageForm(
                titleText = "공연 컨셉 이미지"
            )

            description = TitleInputForm(
                titleText = "공연 소개",
                hintText = "공연 소개를 입력하세요."
            )
            openConcertForm.value.concertIntroduce = description // new

            genre = TitleCapsuleSearchForm(
                titleText = "장르",
                hintText = "장르 검색",
                searchList = arrayOf("인디 록", "인디 음악", "인디 팝", "인디 포크", "재즈", "재즈 랩", "재즈 록", "재즈 팝"),
                capacity = 3
            )
            openConcertForm.value.concertGenre = genre

            val (startDateTime, endDateTime) = TitleStartEndTimeForm(
                titleText = "공연 시간",
                startTitle = "공연 시작 예정 시간",
                endTitle = "공연 종료 예정 시간"
            )
            openConcertForm.value.concertStartTime = startDateTime // new
            openConcertForm.value.concertEndTime = endDateTime // new

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

//            concertLocation = TitleInputForm(
//                titleText = "공연 장소",
//                hintText = "공연 장소를 입력하세요."
//            )
//
//            detailLocation = TitleInputForm(
//                titleText = "상세 주소",
//                hintText = "상세 주소를 입력하세요."
//            )

            val ai = AddressForm()
            openConcertForm.value.concertLocation = ai?.address ?: ("" + ai?.addressDetail)

            ticketPrice = TitleInputForm(
                titleText = "최소 후원금",
                hintText = "단위 : 원",
                isNumber = true
            )

            // new
            val price = try {
                Integer.parseInt(ticketPrice)
            }catch (e:Exception){
                -1
            }
            openConcertForm.value.concertMinPrice = price // new

            tag = TitleCapsuleSearchForm(
                titleText = "태그",
                hintText = "태그 검색",
                searchList = arrayOf(),
                capacity = 5,
                isTagCapsule = true
            )
            openConcertForm.value.concertTags = tag;

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    if(checkOpenConcertForm(
                        arrayOf(
                            openConcertForm.value.concertName,
                            openConcertForm.value.concertIntroduce,
                            openConcertForm.value.concertStartTime.toString(),
                            openConcertForm.value.concertEndTime.toString()
                        )
                    )
                    // || openConcertForm.value.concertGenre.isNotEmpty()
                    // || openConcertForm.value.concertTags.isNotEmpty()
                    || openConcertForm.value.concertMinPrice > 0
                    ){
                        ToastManager.shortToast(context, "공연이 개설되었습니다")
                        // 공연 개설 로직
                        // aaa-000-00
                        val newId = "aaa-000-010"
                        // +convertIdx(DummyValues.concertList.size);
                        DummyValues.concertList.putIfAbsent(newId, ocfAdapter(newId, openConcertForm.value));
                        ToastManager.shortToast(context, DummyValues.concertList[newId]?.concertId?:"공연 개설 안됌");

                        navController.popBackStack()
                    }else {
                        ToastManager.shortToast(context, "공연 개설 양식을 다시 확인해주새요")
                    }
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

fun ocfAdapter(id:String, ocf:OpenConcertForm):ConcertListInfo{
    return ConcertListInfo(
        concertId = id,
        // imgURL = ocf
        artistName = ocf.concertName,
        concertIntroduce = ocf.concertIntroduce,
        location = ocf.concertLocation,
        latitude = GlobalState.lat,
        longitude = GlobalState.lon,
    )
}
fun convertIdx(size:Int):String{
    // 000 ~ 999
    val stIdx = size.toString();
    val header = ""
    while(stIdx.length + header.length <4){
        header.plus("0")
    }
    return header+stIdx;
}

// 입력값 체크
fun checkOpenConcertForm(args:Array<String>):Boolean{
    args.forEach { it -> if(it.isEmpty()) return false }
    return true;
}