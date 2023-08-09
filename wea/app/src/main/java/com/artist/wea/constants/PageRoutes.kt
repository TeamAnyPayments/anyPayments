package com.artist.wea.constants

sealed class PageRoutes(val route:String){
    object Login: PageRoutes("login")
    object Home: PageRoutes("home")
    object UserRegister: PageRoutes("register/common")
    object FindId: PageRoutes("find-id")
    object FindPwd: PageRoutes("find-pwd")
    object ChangePwd: PageRoutes("change-pwd")
    object ChangeEmail: PageRoutes("change-email")
    object SearchConcert: PageRoutes("search-concert")
    object ArtistRank: PageRoutes("artist-rank")
    object Notify: PageRoutes("notify")
    object Setting: PageRoutes("setting")
    object ClientService: PageRoutes("client-service")
    object ConcertBenefit: PageRoutes("concert-benefit")
    object SearchArtist: PageRoutes("search-artist")
    object ArtistInfo: PageRoutes("artist-info")
    object ConcertInfo: PageRoutes("concert-info")
    object MyArtist: PageRoutes("my-artist")

}