package com.artist.wea.screen.activity

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.artist.wea.BuildConfig
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.data.ConcertReceipt
import com.artist.wea.screen.pages.ArtistInfoModifyPage
import com.artist.wea.screen.pages.ArtistInfoPage
import com.artist.wea.screen.pages.ArtistJoinPage
import com.artist.wea.screen.pages.ArtistProfileListPage
import com.artist.wea.screen.pages.ArtistQuitPage
import com.artist.wea.screen.pages.ArtistRankPage
import com.artist.wea.screen.pages.ChangeEmailPage
import com.artist.wea.screen.pages.ChangePwdPage
import com.artist.wea.screen.pages.ClientServicePage
import com.artist.wea.screen.pages.ConcertBenefitPage
import com.artist.wea.screen.pages.ConcertInfoPage
import com.artist.wea.screen.pages.FindIdPage
import com.artist.wea.screen.pages.FindPwdPage
import com.artist.wea.screen.pages.HomePage
import com.artist.wea.screen.pages.LoginPage
import com.artist.wea.screen.pages.MemberAddPage
import com.artist.wea.screen.pages.MemberManagePage
import com.artist.wea.screen.pages.MyArtistPage
import com.artist.wea.screen.pages.NotifyPage
import com.artist.wea.screen.pages.OpenConcertPage
import com.artist.wea.screen.pages.PaymentsManagePage
import com.artist.wea.screen.pages.SearchArtistPage
import com.artist.wea.screen.pages.SearchConcertPage
import com.artist.wea.screen.pages.SettingPage
import com.artist.wea.screen.pages.TicketListPage
import com.artist.wea.screen.pages.TicketPage
import com.artist.wea.screen.pages.UserProfilePage
import com.artist.wea.screen.pages.UserQuitPage
import com.artist.wea.screen.pages.UserRegisterPage
import com.artist.wea.ui.theme.WeaTheme
import com.artist.wea.util.CommonUtils
import com.artist.wea.util.CommonUtils.Companion.checkLoginInfo
import com.artist.wea.util.CommonUtils.Companion.getSerializable
import com.artist.wea.util.PermissionChecker
import com.artist.wea.util.ToastManager.Companion.shortToast
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.map.NaverMapSdk

class MainActivity : ComponentActivity() {

    private lateinit var permissionChecker:PermissionChecker;
    private lateinit var fusedLocationClient: FusedLocationProviderClient;


    override fun onResume() {
        super.onResume()
        detectPaymentsResult(this) // intent 결과 수신
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionChecker = PermissionChecker(this, this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this) // 위치 서비스 클라이언트 초기화


        setContent {
            // googleADCheck
            googleADCheck(this) // 구글 광고 환경 체크
            // naverMapInit
            naverMapInit(this) // 네이버 맵 init
            // checkLoginInfo
            val isLogin = checkLoginInfo(this) // 로그인 여부 체크
            val navController = rememberNavController()
            // 위치 업데이트 요청
            if(isLogin){
                // 위치 권한 확인 및 요청
                CommonUtils.checkLocationPermission(this)
                CommonUtils.requestLocationUpdates(this, fusedLocationClient)
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
                        type = navBackStackEntry.arguments?.getString("type")
                    )
                }
                composable(PageRoutes.FindId.route) { FindIdPage(navController=navController) }
                composable(PageRoutes.FindPwd.route) { FindPwdPage(navController=navController) }
                composable(PageRoutes.ChangePwd.route) { ChangePwdPage(navController=navController) }
                composable(PageRoutes.ChangeEmail.route) { ChangeEmailPage(navController = navController) }
                composable(PageRoutes.SearchConcert.route){ SearchConcertPage(navController = navController) }
                composable(PageRoutes.ArtistRank.route) { ArtistRankPage(navController = navController) }
                composable(PageRoutes.Notify.route) { NotifyPage(navController = navController) }
                composable(PageRoutes.Setting.route) { SettingPage(navController = navController) }
                composable(PageRoutes.ClientService.route) { ClientServicePage(navController = navController) }
                composable(PageRoutes.ConcertBenefit.route) { ConcertBenefitPage(navController = navController) }
                composable(PageRoutes.SearchArtist.route) { SearchArtistPage(navController = navController) }
                composable(PageRoutes.ArtistInfo.route+"/{userId}",
                    arguments = listOf(
                        navArgument("userId"){
                            type = NavType.StringType
                            defaultValue = "abc000"
                         })
                    ) {
                        navBackStackEntry ->
                    ArtistInfoPage(
                        navController = navController,
                    )
                }
                composable(PageRoutes.ConcertInfo.route+"/{concertId}",
                    arguments = listOf(navArgument("concertId"){
                        type = NavType.StringType
                        defaultValue = "0000-0000-0000"
                    })
                ) { navBackStackEntry ->
                    ConcertInfoPage(
                        navController = navController,
                        concertId = navBackStackEntry.arguments?.getString("id")?:""
                    )
                }
                composable(PageRoutes.MyArtist.route) { MyArtistPage(navController = navController) }
                composable(PageRoutes.ArtistInfoModify.route) { ArtistInfoModifyPage(navController = navController) }
                composable(PageRoutes.MemberAdd.route) { MemberAddPage(navController = navController) }
                composable(PageRoutes.ArtistProfileList.route) { ArtistProfileListPage(navController = navController) }
                composable(PageRoutes.MemberManage.route) { MemberManagePage(navController = navController) }
                composable(PageRoutes.UserProfile.route) { UserProfilePage(navController = navController) }
                composable(PageRoutes.PaymentsManage.route) { PaymentsManagePage(navController = navController) }
                composable(PageRoutes.TicketList.route) { TicketListPage(navController = navController) }
                composable(PageRoutes.Ticket.route+"/{ticketId}",
                    arguments = listOf(navArgument("ticketId"){
                        type = NavType.StringType
                        defaultValue = getString(R.string.text_default_ticket_no)
                    })
                ) {
                    navBackStackEntry ->
                    TicketPage(
                        navController = navController,
                        ticketId = navBackStackEntry.arguments?.getString("ticketId")?:
                                        getString(R.string.text_default_ticket_no)
                    )
                }
                composable(PageRoutes.OpenConcert.route) { OpenConcertPage(navController = navController) }
                composable(PageRoutes.ArtistQuit.route) { ArtistQuitPage(navController = navController) }
                composable(PageRoutes.UserQuit.route) { UserQuitPage(navController = navController) }
                composable(PageRoutes.ArtistJoin.route) { ArtistJoinPage(navController = navController) }
            }
        }
    }
    // 권한 요청 결과 처리
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionChecker.PERMISSION_STATE_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // 모든 권한이 허용된 경우
                // 위치 정보를 얻는 작업 수행
            } else {
                // 권한 중 하나라도 거부된 경우
                // 사용자에게 권한이 필요하다고 알릴 수 있습니다.
                shortToast(this, "원활한 앱 사용을 위해 권한을 허용해주세요")
                val result = permissionChecker.requestPermission()
                // if(!result) permissionChecker.openAppSettings(this)

            }
        }
    }
}

// 결제완료 후 데이터를 받는 함수
fun detectPaymentsResult(activity:Activity){
    // intent로 부터 데이터 수신
    try {
        val concertReceipt =
            getSerializable(activity, "completeReceipt", ConcertReceipt::class.java)
    }catch (e:Exception){
    }
}

// 구글 광고와 네이버 맵 광고 세팅은 메인 액티비티에서 체크해주어야 하므로 메인 액티비티 內 함수로 구현
fun googleADCheck(context: Context){
    MobileAds.initialize(context) // 구글 광고 ADMOBS INIT...
    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    val testDeviceIds: List<String> = mutableListOf(androidId)
    val configuration =
        RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
    MobileAds.setRequestConfiguration(configuration)
}
fun naverMapInit(context: Context){
    // 네이버 지도를 위한 SDK Client 등록!
    NaverMapSdk.getInstance(context).client =
        NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_CLIENT_ID) // local.properties로부터 읽어들임
}
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    val navController = rememberNavController()
    WeaTheme {
        LoginPage(navController = navController)
    }
}
