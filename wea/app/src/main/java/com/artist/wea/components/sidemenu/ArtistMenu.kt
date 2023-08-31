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

@Composable
fun ArtistMenu(
    navController: NavHostController,
    modifier: Modifier,
    korTextStyle: TextStyle = getDefTextStyle(),
    menuTextStyle:TextStyle = get14TextStyle()
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
        //
        Text(
            text = "공연 관리",
            style = menuTextStyle,
            modifier = menuModifier
                .clickable {
                    // navController.navigate(PageRoutes.Home.route)
                }
        )
        // 공연 수익
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
                    // navController.navigate(PageRoutes.Home.route)
                }
        )
    }
}