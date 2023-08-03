package com.artist.wea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artist.wea.constants.PageRoutes
import com.artist.wea.pages.HomePage
import com.artist.wea.pages.LoginPage
import com.artist.wea.ui.theme.WeaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = PageRoutes.Login.route) {
                composable(PageRoutes.Login.route) { LoginPage(navController = navController) }
                composable(PageRoutes.Home.route) { HomePage(navController = navController) }
            }

        }
    }
 
}




@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    WeaTheme {
        LoginPage(navController = navController);
    }
}