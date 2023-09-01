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

@Composable
fun GoogleAdItem(
    modifier: Modifier = Modifier,
    isMaxHeight:Boolean = false,
    height: Dp = 50.dp // 32, 50, 90
){

    val boxModifier =
        if(!isMaxHeight) modifier
        .height(height)
        else modifier.fillMaxHeight()

    // 구글 광고 올 부분 todo...
    Box(
        modifier =  boxModifier
            .fillMaxWidth()
            //.background(color = colorResource(id = R.color.mono900))
    ) {

        val adId = stringResource(id = R.string.sample_banner_id)
        val adRequest = AdRequest.Builder().build()
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                context -> AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = adId
                    loadAd(adRequest)
                }
            },
            update = {
                adView -> adView.loadAd(adRequest)
            }
        )
    }
}