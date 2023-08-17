package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.UserInfo
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserProfilePage(
    navController: NavHostController
){

    val userInfo:UserInfo = UserInfo(
        profileURL = "https://mblogthumb-phinf.pstatic.net/MjAyMDAyMDdfMTYw/MDAxNTgxMDg1NzUxMTUy.eV1iEw2gk2wt_YqPWe5F7SroOCkXJy2KFwmTDNzM0GQg.Z3Kd5MrDh07j86Vlb2OhAtcw0oVmGCMXtTDjoHyem9og.JPEG.7wayjeju/%EB%B0%B0%EC%9A%B0%ED%94%84%EB%A1%9C%ED%95%84%EC%82%AC%EC%A7%84_IMG7117.jpg?type=w800",
        userName = "홍길동",
        userId = "mansa_tired_cat",
        email = "tired_cat@email.com"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageTopBar(
            navController = navController,
            pageTitle = "내 프로필"
        )

        // 사용자 프로필 유닛
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GlideImage(
                imageModel = userInfo.profileURL.ifEmpty { R.drawable.icon_def_user_img },
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 100),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                modifier = Modifier
                    .size(156.dp)
                    .clip(shape = RoundedCornerShape(78.dp))
            )

            // 유저 이름
            Text(
                text = userInfo.userName,
                style = getDefTextStyle().copy(fontSize = 20.sp)
            )
            // 유저 아이디
            Text(
                text = userInfo.userId,
                style = getDefTextStyle()
            )
            // 유저 이메일
            Text(
                text = userInfo.email,
                style = getDefTextStyle()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        // 내 정보 관리
        Column(modifier = Modifier
            .padding(16.dp , 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // 나의 정보 관리
            InfoUnit(
                titleText = "나의 정보 관리",
                screen = {}
            )
            // 이메일 변경
            Text(
                text = "이메일 변경",
                style = getDefTextStyle(),
                modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.ChangeEmail.route)
                }
            )
            // 비밀번호 변경
            Text(
                text = "비밀번호 변경",
                style = getDefTextStyle(),
                modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.ChangePwd.route)
                }
            )
            // 소셜 계정 관리
            Text(
                text = "소셜 계정 관리",
                style = getDefTextStyle(),
                modifier = Modifier
            )
            // 로그아웃
            Text(
                text = "로그아웃",
                style = getDefTextStyle(),
                modifier = Modifier
            )
            // 로그아웃
            Text(
                text = "회원탈퇴",
                style = getDefTextStyle()
                    .copy(
                        color = colorResource(id = R.color.red500)
                    ),
                modifier = Modifier
            )
        }


    }
}