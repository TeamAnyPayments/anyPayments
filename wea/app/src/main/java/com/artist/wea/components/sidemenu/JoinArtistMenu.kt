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
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun JoinArtistMenu(
    navController: NavHostController,
    modifier: Modifier,
    korTextStyle: TextStyle = getDefTextStyle(),
    engTextStyle: TextStyle = get14TextStyle(),
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
                .clickable { }
        )
    }
}