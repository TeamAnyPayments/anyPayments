package com.artist.wea.components.sidemenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ArtistMenu(
    navController: NavHostController,
    modifier: Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        SideMenuHeader(
            "작업실",
            badgeName = "ARTIST ONLY",
            badgeColor = colorResource(id = R.color.sky_blue400)
        )
        // Options...
        // 아티스트 프로필 편집
        Text(
            text = "아티스트 프로필 편집",
            style = getDefTextStyle(),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 4.dp)
                .clickable {
                    // navController.navigate(PageRoutes.Home.route)
                }
        )
        //
        Text(
            text = "공연 관리",
            style = getDefTextStyle(),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 4.dp)
                .clickable {
                    // navController.navigate(PageRoutes.Home.route)
                }
        )
        // 공연 수익
        Text(
            text = "공연 수익",
            style = getDefTextStyle(),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 4.dp)
                .clickable {
                    // navController.navigate(PageRoutes.Home.route)
                }
        )
        // 아티스트 탈퇴
        Text(
            text = "아티스트 탈퇴",
            style = getDefTextStyle().copy(color = colorResource(id = R.color.red400)),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 4.dp)
                .clickable {
                    // navController.navigate(PageRoutes.Home.route)
                }
        )


    }

}