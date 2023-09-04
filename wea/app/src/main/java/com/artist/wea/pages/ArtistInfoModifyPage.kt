package com.artist.wea.pages

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.ArtistInfoItem
import com.artist.wea.components.ConcertSearchItem
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.TitleInputForm
import com.artist.wea.components.WeaIconImage
import com.artist.wea.components.WeaWideImage
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.GlobalState.Companion.currentArtistInfo
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.util.PhotoSelector

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArtistInfoModifyPage(
    navController: NavHostController
){
    val context = LocalContext.current

    // Companion State 클래스의 값을 통해 현재 보고 있었던 프로필 정보의 값을 init
    var artistInfo = currentArtistInfo.value

    val currentIdx = remember { mutableStateOf<Int>(0) } // 0 or 1
    val profileBitmap = remember { mutableStateOf<Bitmap?>(null) } // 아티스트 프로필 이미지 >> LOCAL
    val backgroundBitmap = remember { mutableStateOf<Bitmap?>(null) } // 배경화면 이미지 >> LOCAL

    // 사진 불러오기 기능
    val photoSelector = PhotoSelector()
    val takePhotoFromAlbumLauncher = // 갤러리에서 사진 가져오기
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->

                    Log.d("IMAGE:::", "${uri.toString()}")

                    val imgSource = if(currentIdx.value == 0) backgroundBitmap else profileBitmap
                    val fileName = if(currentIdx.value == 0) "artist_background" else "artist_profile"

                    photoSelector.setImageToVariable(
                        context = context,
                        uri = uri,
                        imageSource = imgSource,
                        fileName = fileName
                    )

                } ?: run {
                    Toast.makeText(context, "이미지를 불러오던 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                }
            } else if (result.resultCode != Activity.RESULT_CANCELED) {
                // ??
            }
        }

    val mArtistName = remember { mutableStateOf(artistInfo.artistName) }
    val mComment = remember { mutableStateOf(artistInfo.comment) }
    val mMainIntroduce = remember { mutableStateOf(artistInfo.mainIntroduce) }

    val scrollState = rememberScrollState()
    val height = 164.dp;
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.mono50))
            .verticalScroll(scrollState)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((height.value * 1.7).toInt().dp)
                .background(colorResource(id = R.color.mono50))
        ) {
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
                    .height(height)
                    .align(Alignment.TopStart)
            ) {

                // 배경이미지
                WeaWideImage(
                    modifier = Modifier
                        .align(Alignment.TopStart),
                    imgUrl = artistInfo.bgImgURL,
                    bitmap = backgroundBitmap.value,
                    height = height
                )

                // 배경 이미지 수정 버튼
                Box(modifier = Modifier
                    .padding(16.dp)
                    .size(32.dp)
                    .background(
                        colorResource(id = R.color.color_transparency),
                        shape = CircleShape
                    )
                    .border(1.dp, colorResource(id = R.color.mono50), shape = CircleShape)
                    .align(Alignment.BottomEnd)
                    .clickable {
                        Toast
                            .makeText(context, "배경 사진을 변경 합니다", Toast.LENGTH_SHORT)
                            .show()
                        currentIdx.value = 0 // index 변경
                        // 이미지 가져오기
                        takePhotoFromAlbumLauncher.launch(photoSelector.takePhotoFromAlbumIntent)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_carmera),
                        contentDescription = "이미지 수정",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                            .align(Alignment.Center),
                        tint = colorResource(id = R.color.mono50)
                    )
                }
            }
            // 아티스트 정보 layer
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.Center)
                    .zIndex(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 아티스트 프로필
                Box(
                    modifier = Modifier.size(144.dp)
                ) {
                    // 프로필 이미지
                    WeaIconImage(
                        modifier = Modifier.align(Alignment.Center),
                        imgUrl = artistInfo.profileImgURL,
                        size = 144.dp,
                        bitmap = profileBitmap.value,
                        isClip = true
                    )

                    // 프로필 이미지 수정 버튼
                    Box(modifier = Modifier
                        .padding(18.dp)
                        .size(32.dp)
                        .background(
                            colorResource(id = R.color.color_transparency),
                            shape = CircleShape
                        )
                        .border(1.dp, colorResource(id = R.color.mono50), shape = CircleShape)
                        .align(Alignment.BottomEnd)
                        .clickable {
                            Toast
                                .makeText(context, "프로필 사진을 변경 합니다", Toast.LENGTH_SHORT)
                                .show()
                            // 이미지 가져오기
                            currentIdx.value = 1 // index 변경
                            takePhotoFromAlbumLauncher.launch(photoSelector.takePhotoFromAlbumIntent)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_carmera),
                            contentDescription = "이미지 수정",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(4.dp)
                                .align(Alignment.Center),
                            tint = colorResource(id = R.color.mono50)
                        )
                    }
                }
            }
        }

        // 이름, 한줄소개, 프로필 수정
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp, start = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // 아티스트 이름
            mArtistName.value = TitleInputForm(
                titleText = "아티스트 이름",
                defaultText = artistInfo.artistName,
                // hintText = ""
            )

            // 한줄 소개
            mComment.value = TitleInputForm(
                titleText = "한줄 소개",
                defaultText = artistInfo.comment
            )

            // 프로필 소개
            mMainIntroduce.value = TitleInputForm(
                titleText = "프로필 소개",
                defaultText = artistInfo.mainIntroduce
            )

        }

        // 멤버 소개
        val artistInfoList = DummyValues().memberList

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
            rightMenuIcon = Icons.Filled.Edit,
            rightMenuAction = {
                navController.navigate(PageRoutes.MemberManage.route)
            }
        )

        // 공연 이력
        val concertList = DummyValues().concertLogList

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
                            .wrapContentHeight()
                    )
                }
            },
            screenModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(64.dp))

    }
}
