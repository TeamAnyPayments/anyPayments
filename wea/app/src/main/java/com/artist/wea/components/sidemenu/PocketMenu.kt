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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle

@Composable
fun PocketMenu(
    navController: NavHostController,
    modifier: Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        SideMenuHeader(
            "보관함",
            "POCKET"
        )
        // Options...
        Text(
            text = "My 아티스트",
            style = getDefTextStyle(),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 4.dp)
                .clickable {
                    navController.navigate(PageRoutes.MyArtist.route)
                }
        )
    }
}