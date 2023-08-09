package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.PageRoutes

@Composable
fun ProfileListPage(
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
        Button(onClick = { navController.navigate(PageRoutes.MemberManage.route) }) {
            Text("멤버 관리 이동")
        }

    }

}