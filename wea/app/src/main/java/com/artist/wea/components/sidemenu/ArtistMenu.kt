package com.artist.wea.components.sidemenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

// 아티스트일 경우 표시 할 메인페이지 우측 사이드 메뉴 컴포저블
@Composable
fun ArtistMenu(
    navController: NavHostController, // 하위 컴포저블에 네비게이션 컨트롤러를 전달하기 위해 파라미터로 받음
    modifier: Modifier,
    // 사이드 메뉴의 제목과 실제 메뉴의 텍스트 속성을 편집하기 위한 파라미터들
    menuTextStyle:TextStyle = get14TextStyle(), // 하위 메뉴들의 텍스트 속성
    korTextStyle: TextStyle = getDefTextStyle() // 메뉴 제목의 한글 텍스트 속성
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ){

        val menuModifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(start = 4.dp)

        // 사이드 메뉴의 헤더
        SideMenuHeader(
            korMenuText = "작업실",
            korTextStyle = korTextStyle,
            badgeName = "ARTIST ONLY",
            badgeColor = colorResource(id = R.color.sky_blue400)
        )
        // Options...
        // 아티스트 프로필 편집
        Text(
            text = "아티스트 프로필 편집",
            style = menuTextStyle,
            modifier = menuModifier
                .clickable {
                    navController.navigate(PageRoutes.ArtistProfileList.route)
                }
        )
        // 아티스트가 공연 관리할 때 메뉴
        Text(
            text = "공연 관리",
            style = menuTextStyle,
            modifier = menuModifier
                .clickable {
                    // navController.navigate(PageRoutes.Home.route)
                }
        )
        // 공연 수익 메뉴, TODO 토스 결제 기능 완성 시, 아티스트 별 총 결제액을 산정해야함!
        Text(
            text = "공연 수익",
            style = menuTextStyle,
            modifier = menuModifier
                .clickable {
                    navController.navigate(PageRoutes.ConcertBenefit.route)
                }
        )
        // 아티스트 탈퇴
        Text(
            text = "아티스트 탈퇴",
            style  = menuTextStyle
                .copy(color = colorResource(id = R.color.red400)),
            modifier = menuModifier
                .clickable {
                    navController.navigate(PageRoutes.ArtistQuit.route)
                }
        )
    }
}