package com.artist.wea.components.sidemenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

// 일반 회원 → 아티스트로 신청하는 메뉴!
@Composable
fun JoinArtistMenu(
    navController: NavHostController, // 하위 컴포저블에 네비게이션 컨트롤러를 전달하기 위해 파라미터로 받음
    modifier: Modifier,
    // 사이드 메뉴의 제목과 실제 메뉴의 텍스트 속성을 편집하기 위한 파라미터들
    korTextStyle: TextStyle = getDefTextStyle(), // 메뉴 제목의 한글 텍스트 속성
    engTextStyle: TextStyle = get14TextStyle(), // 메뉴 제목의 영어 텍스트 속성
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        SideMenuHeader(
            "아티스트 등록",
            "Be a Artist",
            korTextStyle = korTextStyle,
            engTextStyle = engTextStyle,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clickable {
                    navController.navigate(PageRoutes.ArtistJoin.route)
                }
        )
    }
}