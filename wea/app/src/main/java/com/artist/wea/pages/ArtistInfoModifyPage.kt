package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ArtistInfoItem
import com.artist.wea.components.ConcertSearchItem
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.ModifyForm
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.uidtclass.SearchArtistInfo
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ArtistInfo
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ArtistInfoModifyPage(
    navController: NavHostController
){

    // TODO navController를 통해서 아티스트 데이터를 추출해서 렌더링 하도록 설계
    var artistInfo = ArtistInfo(
        profileImgURL = "https://image.kmib.co.kr/online_image/2014/1015/201410152053_61170008765071_1.jpg",
        bgImgURL = "https://img.hankyung.com/photo/202205/01.29843403.1.jpg",
        artistName = "ENJOY",
        comment = "안녕하세요, 행복을 노래하는 가수입니다.",
        mainIntroduce = "안녕하세요 Sparrow Spirit!\n" +
                "\n" +
                "홍대 스트릿 버스커 그룹 로드 버스킹입니다.\n" +
                "\n" +
                "어디서든 관객 여러분과 특별한 추억을 쌓아가기 위하여 여러 지역에서 버스킹을 하고 있습니다.\n" +
                "\n" +
                "음악을 사랑한다는 마음 하나라면,\n" +
                "우리가 있는 이 곳의 온도는 뜨거울 거에요.\n" +
                "\n" +
                "창립일\n" +
                "2013. 01. 16.\n" +
                "\n" +
                "자주 출몰하는 장소\n" +
                "홍대입구 2번 출구, 강남역 3번 출구\n" +
                "\n" +
                "인스타그램\n" +
                "@abc_123_heart",
    )

    val mProfileImgURL = remember { mutableStateOf(artistInfo.profileImgURL) }
    val mBgImgURL = remember { mutableStateOf(artistInfo.bgImgURL) }
    val mArtistName = remember { mutableStateOf(artistInfo.artistName) }
    val mComment = remember { mutableStateOf(artistInfo.comment) }
    val mMainIntroduce = remember { mutableStateOf(artistInfo.mainIntroduce) }

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50))
            .verticalScroll(scrollState)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(328.dp)
        ){
            // 상단 바
            PageTopBar(
                navController = navController,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .zIndex(2f),
                hasTransparency = true,
                rightMenuText = "저장",
                rightMenuAction = { navController.popBackStack() },
                rightMenuTextStyle = get14TextStyle()
                    .copy(color = colorResource(id = R.color.white)),
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
                    .align(Alignment.TopStart)
            ){
                // 배경 이미지
                GlideImage(
                    imageModel = artistInfo.bgImgURL.ifEmpty { R.drawable.icon_def_user_img },
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
                    circularReveal = CircularReveal(duration = 200),
                    // shows a placeholder ImageBitmap when loading.
                    placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                    // shows an error ImageBitmap when the request failed.
                    error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(144.dp)
                        .align(Alignment.TopStart)
                )
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "이미지 수정",
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    tint = colorResource(id = R.color.white)
                )
            }


            // 아티스트 정보 layer
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.BottomCenter)
                    .zIndex(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 아티스트 프로필
                Box(
                    modifier = Modifier.size(144.dp)
                ) {
                    GlideImage(
                        imageModel = artistInfo.profileImgURL.ifEmpty { R.drawable.icon_def_user_img } ,
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 200),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                        // shows an error ImageBitmap when the request failed.
                        error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(144.dp)
                            .height(144.dp)
                            //.size(144.dp)
                            .clip(shape = RoundedCornerShape(72.dp))
                    )
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "이미지 수정",
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        tint = colorResource(id = R.color.white)
                    )
                }

                // 아티스트 이름
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    mArtistName.value = ModifyForm(
                        hintText = mArtistName.value,
                        modifier = Modifier
                            .width(128.dp)
                            .wrapContentHeight(),
                        textStyle = get14TextStyle().copy(
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.mono800)
                        )
                    )
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "북마크",
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(id = R.color.kakao_yellow) // TODO 북마크 조건부 분기 처리
                    )
                }
                mComment.value = ModifyForm(
                    hintText = mComment.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(24.dp, 0.dp)
                    ,
                    textStyle = get14TextStyle().copy(
                        textAlign = TextAlign.Start,
                        color = colorResource(id = R.color.mono800)
                    )
                )
            }

            // bottom layer
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .background(color = colorResource(id = R.color.mono50))
                .align(Alignment.BottomEnd)
            )
        }

        // 프로필 소개
        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "프로필 소개",
            screen = {
                mMainIntroduce.value = ModifyForm(
                    hintText = mMainIntroduce.value,
                    textStyle = getDefTextStyle()
                        .copy(color = colorResource(id = R.color.mono800)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                )
            },
        )

        // 멤버 소개
        val artistInfoList = arrayOf<ArtistInfo>(
            ArtistInfo(
                "https://images.ctfassets.net/lnhrh9gqejzl/4a2YAZ0WkM4AcsYciUQMWA/15a089fc77c7dc9953ace8a3b23fdcde/2018-03-07.gif?fm=jpg",
                artistName = "블라블라 아티스트",
                comment = "좋은 음악 함께 공유해요"
            ),
            ArtistInfo(
                "https://c8.alamy.com/comp/HYY9TT/profile-of-a-girl-and-crayons-HYY9TT.jpg",
                artistName = "무슨무슨 아티스트",
                comment = "이번 생은 처음이라... 모든걸 다 잘할 순 없잖아"
            ),
            ArtistInfo(
                "https://i.pinimg.com/236x/38/9a/77/389a77c6b7f44bab5d3076365b306527--vektor-scarlett-ohara.jpg",
                artistName = "쏼라쏼라 아티스트",
                comment = "무더운 여름 밤, 한강 공원에서 함께 힐링할래요?"
            )
        )

        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "Member",
            screen = {
                artistInfoList.forEachIndexed { index, artistInfo ->
                    ArtistInfoItem(
                        navController = navController,
                        artistInfo = artistInfo,
                        hasLine = false,
                        isActive = false
                    )
                }
            },
            rightMenuIcon = Icons.Filled.AddCircle,
            rightMenuAction = {
                navController.navigate(PageRoutes.MemberAdd.route)
            }
        )

        // 공연 이력
        val concertList = arrayOf<SearchArtistInfo>(
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
        val scrollState = rememberScrollState()
        // 가변적 modifier
        val isLongList = concertList.size > 4;
        val minModifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
        val defModifier = Modifier
            .fillMaxWidth()
            .height(392.dp)
            .verticalScroll(scrollState)

        // History
        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "History",
            screen = {
                concertList.forEach {
                        item ->
                    ConcertSearchItem(
                        navController = navController,
                        content = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        isActive = false
                    )
                }
            },
            screenModifier = if(isLongList) defModifier else minModifier
        )

        Spacer(modifier = Modifier.height(64.dp))

    }

}