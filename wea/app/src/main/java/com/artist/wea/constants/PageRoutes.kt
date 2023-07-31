package com.artist.wea.constants

sealed class PageRoutes(val route:String){
    object OverView: PageRoutes("login")
    object MealPlan: PageRoutes("home")

}