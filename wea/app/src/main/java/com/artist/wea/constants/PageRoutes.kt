package com.artist.wea.constants

sealed class PageRoutes(val route:String){
    object Login: PageRoutes("login")
    object Home: PageRoutes("home")

}