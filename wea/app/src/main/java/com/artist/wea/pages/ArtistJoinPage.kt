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
            pageTitle = "아티스트 등록"
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
                    titleText = "사용하실 예명은 무엇인가요? *",
                    hintText = "사용하실 예명을 입력해주세요"
                )
                // 아티스트 한 줄 소개
                TitleInputForm(
                    titleText = "자신을 소개할 한 줄 소개를 작성해주세요. *",
                    hintText = "한 줄로 자신을 소개하는 한마디를 작성해보세요."
                )

                // 프로필 등록
                TitleImageInputForm(
                    titleText = "프로필 사진을 등록해주세요. *",
                    size = 128.dp
                )

                // 배경 이미지 등록
                TitleGuideImageInputForm(
                    titleText = "배경 이미지를 등록해주세요.",
                    guideText = "배경 이미지 미등록 시 기본 이미지로 대체됩니다.",
                    size = 144.dp
                )

                // 아티스트 소개 (긴 소개글)
                TitleInputForm(
                    titleText = "자신을 소개해주세요. *",
                    hintText = "이곳에 작성한 소개는 다른 사용자가 회원님의 정보를 조회할 때 노출됩니다."
                )

                // 활동지역
                LocationInputForm(
                    titleText = "주로 활동하시는 지역은 어디인가요?"
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