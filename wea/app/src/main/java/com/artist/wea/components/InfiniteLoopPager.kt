package com.artist.wea.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteLoopPager(
    modifier: Modifier = Modifier,
    list: List<String> = listOf(),
    height:Dp = 144.dp,
    posIdx:Int = 0,
    delay:Long = 2500L
) {
    val pagerState = rememberPagerState()

    // 초기페이지 설정. 한번만 실행되기 원하니 key 는 Unit|true.
    LaunchedEffect(key1 = Unit) {
        // 최대한 많은 페이지 양쪽으로 보여주기 위함.
        var initialPage = Int.MAX_VALUE / 2

        // 초기페이지를 0으로 잡는다.
        while (initialPage % list.size != 0) {
            initialPage++
        }
        pagerState.scrollToPage(initialPage)
    }

    // 지정한 시간마다 auto scroll.
    // 유저의 스크롤해서 페이지가 바뀐경우 다시 실행시키고 싶기 때문에 key는 currentPage.
    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            while (true) {
                delay(delay)
                // 페이지 바뀌었다고 애니메이션이 멈추면 어색하니 NonCancellable
                withContext(NonCancellable) {
                    // 일어날린 없지만 유저가 약 10억번 스크롤할지 몰라.. 하는 사람을 위해..
                    if (pagerState.currentPage + 1 in 0..Int.MAX_VALUE) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        }
    }

    Box(modifier = modifier,

        ) {
        HorizontalPager(
            pageCount = Int.MAX_VALUE,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), // PageSize.Fill 상태에서 비율만 줘보기.
            state = pagerState,
        ) { index ->
            // index % (list.size) 나머지 값으로 인덱스 가져오기. 안전하게 getOrNull 처리.
            list.getOrNull(index % (list.size))?.let { url ->
                GlideImage(
                    imageModel = url,
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows a placeholder ImageBitmap when loading.
                    placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                    // shows an error ImageBitmap when the request failed.
                    error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height)
                )
            }
        }
        // 추가됨
        PagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp),
            count = list.size,
            dotSize = 6.dp,
            currentPage = pagerState.currentPage % list.size,
            selectedColor = colorResource(id = R.color.white),
            unSelectedColor = colorResource(id = R.color.mono300),
            posIdx = posIdx
        )
    }
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    dotSize: Dp,
    currentPage: Int,
    selectedColor: Color,
    unSelectedColor: Color,
    posIdx:Int = 0,
) {
    val pos:Arrangement.Horizontal = when (posIdx) {
        1 -> Arrangement.Start
        2 -> Arrangement.End
        else -> Arrangement.Center
    }

    Row(modifier = modifier
        .padding(horizontal = 8.dp),
        horizontalArrangement = pos ) {
        (0 until count).forEach { index ->
            Spacer(modifier = Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .background(
                        color = if (index == currentPage) {
                            selectedColor
                        } else {
                            unSelectedColor
                        },
                        shape = CircleShape
                    )
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
    }
}