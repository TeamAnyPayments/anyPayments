package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.Badge
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.ProfileItem
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.PageRoutes

@Composable
fun ArtistProfileListPage(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = "아티스트 프로필 편집"
        )

        val memberList = DummyValues().crewList

        // list..
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            memberList.forEachIndexed{
                    idx, item ->
                ProfileItem(
                    navController = navController,
                    content = item,
                    destination = {
                        navController.navigate(PageRoutes.ArtistInfoModify.route)
                    },
                    rightComposable = {
                        Badge(
                            text = if(idx==0) "리더" else "멤버",
                            color = if(idx==0) colorResource(id = R.color.sky_blue300)
                                    else colorResource(id = R.color.pastel_purple100),
                            txtColor = colorResource(id = R.color.white),
                        )
                    },
                )
            }
        }
    }
}
