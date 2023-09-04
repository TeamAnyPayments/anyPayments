package com.artist.wea.pages

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleGuideImageInputForm
import com.artist.wea.components.TitleImageInputForm
import com.artist.wea.components.TitleInputForm
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ArtistJoinPage(
    navController: NavHostController
){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.mono50)))
    {
        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_artist_join)
        )
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // 아티스트 예명
                TitleInputForm(
                    titleText = stringResource(R.string.text_title_artist_name),
                    hintText = stringResource(R.string.text_hint_artist_name)
                )
                // 아티스트 한 줄 소개
                TitleInputForm(
                    titleText = stringResource(R.string.text_title_artist_introduce),
                    hintText = stringResource(R.string.text_hint_artist_introduce)
                )

                // 프로필 등록
                TitleImageInputForm(
                    titleText = stringResource(R.string.text_title_artist_profile_img),
                    size = 128.dp
                )

                // 배경 이미지 등록
                TitleGuideImageInputForm(
                    titleText = stringResource(R.string.text_title_artist_backrground_img),
                    guideText = stringResource(R.string.text_guide_artist_backrground),
                    size = 144.dp
                )

                // 아티스트 소개 (긴 소개글)
                TitleInputForm(
                    titleText = stringResource(R.string.text_title_artist_full_introduce),
                    hintText = stringResource(R.string.text_hint_artist_full_introduce)
                )

                // 활동지역
                LocationInputForm(
                    titleText = stringResource(R.string.text_title_location)
                )
                Spacer(modifier = Modifier.height(64.dp))

            }
            // 하단 버튼
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
                    .background(color = colorResource(id = R.color.dark_orange300))
            ){
                Text(
                    text = "아티스트 등록 신청하기",
                    style = getDefTextStyle()
                        .copy(color = colorResource(id = R.color.white)),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

//TODO... 다음의 주소 API를 사용해서 웹뷰로 주소를 요청한다.
@Composable
fun LocationInputForm(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    titleText:String = stringResource(id = R.string.empty_text)
){
    Column(modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
    ) {
        // input 컴포넌트 제목 부분
        Text(
            text = titleText,
            style = getDefTextStyle()
        )

        val context = LocalContext.current
        val webViewClient = WebViewClient()
        AndroidView(
            factory = {
                WebView(context).apply {
                    this.webViewClient = webViewClient
                    this.loadUrl("https://www.google.com/") // TODO...
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
        )

    }

}