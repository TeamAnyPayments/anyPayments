package com.artist.wea.pages

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.artist.wea.constants.PageRoutes

@Composable
fun HomePage(navController: NavController){

    Button(onClick = {navController.navigate(PageRoutes.Login.route)}) {
        Text(text = "로그인 페이지로 이동")
    }

}