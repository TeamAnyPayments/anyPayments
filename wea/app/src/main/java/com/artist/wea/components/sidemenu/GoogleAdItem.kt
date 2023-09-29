package com.artist.wea.components.sidemenu

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.artist.wea.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

// 구글 광고를 다룰 컨테이너 용 컴포저블
@Composable
fun GoogleAdItem(
    modifier: Modifier = Modifier,
    isMaxHeight:Boolean = false,
    height: Dp = 50.dp // 광고 아이템의 height 사이즈는 3종류 (32, 50, 90 ) (dp)
){

    val boxModifier =
        if(!isMaxHeight) modifier
        .height(height)
        else modifier.fillMaxHeight()

    // 구글 광고 올 부분 TODO...
    Box(
        modifier =  boxModifier
            .fillMaxWidth()
    ) {

        // 구글 광고 표시하는 부분
        val adId = stringResource(id = R.string.sample_banner_id)
        val adRequest = AdRequest.Builder().build()
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                context -> AdView(context).apply {
                    setAdSize(AdSize.BANNER) // 광고 사이즈의 옵션
                    adUnitId = adId // 광고 아이디
                    loadAd(adRequest) // 광고 주세요~ 요청 보내는 부분
                }
            },
            update = {
                adView -> adView.loadAd(adRequest)
            }
        )
    }
}