package com.artist.wea

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.artist.wea.constants.PageRoutes
import com.artist.wea.instance.Retrofit
import com.artist.wea.pages.ArtistInfoModifyPage
import com.artist.wea.pages.ArtistInfoPage
import com.artist.wea.pages.ArtistProfileListPage
import com.artist.wea.pages.ArtistRankPage
import com.artist.wea.pages.ChangeEmailPage
import com.artist.wea.pages.ChangePwdPage
import com.artist.wea.pages.ClientServicePage
import com.artist.wea.pages.ConcertBenefitPage
import com.artist.wea.pages.ConcertInfoPage
import com.artist.wea.pages.FindIdPage
import com.artist.wea.pages.FindPwdPage
import com.artist.wea.pages.HomePage
import com.artist.wea.pages.LoginPage
import com.artist.wea.pages.MemberAddPage
import com.artist.wea.pages.MemberManagePage
import com.artist.wea.pages.MyArtistPage
import com.artist.wea.pages.NotifyPage
import com.artist.wea.pages.PaymentsManagePage
import com.artist.wea.pages.SearchArtistPage
import com.artist.wea.pages.SearchConcertPage
import com.artist.wea.pages.SettingPage
import com.artist.wea.pages.UserProfilePage
import com.artist.wea.pages.UserRegisterPage
import com.artist.wea.ui.theme.WeaTheme
import com.artist.wea.util.PreferenceUtil
import com.naver.maps.map.NaverMapSdk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // 네이버 지도를 위한 SDK Client 등록!
            NaverMapSdk.getInstance(this).client =
                NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_CLIENT_ID) // local.properties로부터 읽어들임
            // 로그인 시 로그인 페이지로 이동하지 않도록, 자동로그인을 위한 변수 /* TODO */
            val context = LocalContext.current;
            val prefs = PreferenceUtil(context);
            val token = prefs.getString("token", "");
            val isLogin = token.isNotEmpty()
            val navController = rememberNavController()
            if(isLogin){ // temp... : 토큰정보 확인용
                Log.d("MAIN_ACTIVITY:::", "토큰 정보 있음")
                Retrofit.token.value = token
            }

            NavHost(
                navController = navController,
                startDestination = if(isLogin) PageRoutes.Home.route
                                    else PageRoutes.Login.route )
            {
                composable(PageRoutes.Login.route) { LoginPage(navController = navController) }
                composable(PageRoutes.Home.route) { HomePage(navController = navController) }
                composable("register/{type}",
                    arguments = listOf(navArgument("type"){
                        type = NavType.StringType
                        defaultValue = "common"
                    }))
                {
                    navBackStackEntry ->
                    UserRegisterPage(
                        navController = navController,
                        navBackStackEntry.arguments?.getString("type")
                    )
                }
                composable(PageRoutes.FindId.route) { FindIdPage(navController=navController) }
                composable(PageRoutes.FindPwd.route) { FindPwdPage(navController=navController) }
                composable(PageRoutes.ChangePwd.route) {ChangePwdPage(navController=navController) }
                composable(PageRoutes.ChangeEmail.route) { ChangeEmailPage(navController = navController) }
                composable(PageRoutes.SearchConcert.route){ SearchConcertPage(navController = navController) }
                composable(PageRoutes.ArtistRank.route) {ArtistRankPage(navController = navController) }
                composable(PageRoutes.Notify.route) { NotifyPage(navController = navController) }
                composable(PageRoutes.Setting.route) { SettingPage(navController = navController) }
                composable(PageRoutes.ClientService.route) { ClientServicePage(navController = navController) }
                composable(PageRoutes.ConcertBenefit.route) { ConcertBenefitPage(navController = navController) }
                composable(PageRoutes.SearchArtist.route) { SearchArtistPage(navController = navController)}
                composable(PageRoutes.ArtistInfo.route) { ArtistInfoPage(navController = navController) }
                composable(PageRoutes.ConcertInfo.route) { ConcertInfoPage(navController = navController) }
                composable(PageRoutes.MyArtist.route) { MyArtistPage(navController = navController) }
                composable(PageRoutes.ArtistInfoModify.route) { ArtistInfoModifyPage(navController = navController) }
                composable(PageRoutes.MemberAdd.route) { MemberAddPage(navController = navController) }
                composable(PageRoutes.ArtistProfileList.route) { ArtistProfileListPage(navController = navController) }
                composable(PageRoutes.MemberManage.route) { MemberManagePage(navController = navController) }
                composable(PageRoutes.UserProfile.route) { UserProfilePage(navController = navController) }
                composable(PageRoutes.PaymentsManage.route) { PaymentsManagePage(navController = navController) }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    WeaTheme {
        LoginPage(navController = navController)
    }
}