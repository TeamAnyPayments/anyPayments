package com.artist.wea.constants

sealed class PageRoutes(val route:String){
    object Login: PageRoutes("login")
    object Home: PageRoutes("home")
    object UserRegister: PageRoutes("register/common")
    object FindId: PageRoutes("find-id")
    object FindPwd: PageRoutes("find-pwd")
    object ChangePwd: PageRoutes("change-pwd")
    object ChangeEmail: PageRoutes("change-email")

}