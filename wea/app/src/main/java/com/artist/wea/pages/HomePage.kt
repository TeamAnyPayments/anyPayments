package com.artist.wea.pages

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.HomeMenuBox
import com.artist.wea.components.InfiniteLoopPager
import com.artist.wea.components.sidemenu.ArtistMenu
import com.artist.wea.components.sidemenu.CSMenu
import com.artist.wea.components.sidemenu.GoogleAdItem
import com.artist.wea.components.sidemenu.JoinArtistMenu
import com.artist.wea.components.sidemenu.PaymentManageMenu
import com.artist.wea.components.sidemenu.PocketMenu
import com.artist.wea.components.sidemenu.ProfileItem
import com.artist.wea.components.sidemenu.SettingMenu
import com.artist.wea.components.sidemenu.TicketMenu
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.UserProfile
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.JSONParser
import com.artist.wea.util.PreferenceUtil
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavHostController,
) {
    val menuOpen = remember { mutableStateOf(false) } // 메뉴 상태를 기억하는 변수
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // 디바이스 스크린 width
    val menuOffset = animateDpAsState(if (menuOpen.value) 0.dp else screenWidth, label = "") // 애니메이션 상태
    val isArtist = true; // 아티스트인지 여부를 판단할 변수

    // 비동기 통신을 위한 기본 객체 settings
    val context = LocalContext.current;
    val mOwner = LocalLifecycleOwner.current
    val repository = RegisterRepository()
    val viewModel = RegisterViewModel(repository)

    // prefs
    val prefs = PreferenceUtil(context);
    val profileJson = remember{
        mutableStateOf(prefs.getString("profile_json", ""))
    }
    val userProfile = remember { mutableStateOf(UserProfile()) }
    val jParser = JSONParser() // json parser

    // 홈페이지 렌더링 시 사용자 정보를 가져옴
    if(profileJson.value.isEmpty()){
        viewModel.getUserInfo()
        viewModel.getUserInfoRes.observe(mOwner, Observer {
            if(it != null ){
                Log.d("GET_USER_INFO:::", "${it.toString()}")
                val jsonString = it.toString()
                prefs.setString("profile_json", "$jsonString") // prefs 저장
                profileJson.value = jsonString // 현재 상태에도 저장
                userProfile.value = jParser.parseJsonToUserProfile(it)
                Log.d("HOME_PAGE:::", "서버 >>> ${profileJson.value}")
            }else {
//                Log.d("PROFILE_PAGE:::", "토큰 만료")
//                Toast.makeText(context, "회원 정보가 만료되었습니다.", Toast.LENGTH_SHORT).show()
//                navController.navigate(PageRoutes.Login.route){
//                    popUpTo(0)
//                }
            }
        })
    }else {
        Log.d("HOME_PAGE:::", "캐싱 >>> ${profileJson.value}")
        val json = JSONObject(profileJson.value)
        userProfile.value = jParser.parseJsonToUserProfile(json)
    }

    //
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 제목
                    Text(
                        text = "우리동네 아티스트, WE:A",
                        style = getDefTextStyle()
                    )
                }
            },
            // 우측 메뉴들
            actions = {
                IconButton(onClick = {
                    navController.navigate(PageRoutes.Notify.route) // 알림 페이지 이동
                }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Alert",
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(id = R.color.mono900)
                    )
                }
                IconButton(onClick = {
                    menuOpen.value = !menuOpen.value // 사이드 메뉴 열기
                }) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(id = R.color.mono900)
                    )
                }
            }
        )
        homePage(
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            isArtist = isArtist)
    }
    // Side Menu Area
    Row{
        // 사이드 메뉴 여백 필터
        Box(modifier =
        Modifier
            .offset(x = menuOffset.value)
            .fillMaxWidth()
            .fillMaxHeight()
            .weight(1.5f)
            .background(color = Color(red = 0, blue = 0, green = 0, alpha = 10))
            .clickable {
                menuOpen.value = false
            }
        )
        val sideScrollState = rememberScrollState(); // 사이드 메뉴용 스크롤 state
        // 사이드 메뉴
        Column(
            modifier = Modifier
                .offset(x = menuOffset.value) // 애니메이션 값을 적용
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(2.5f)
                .background(colorResource(id = R.color.mono50))
                .border(1.dp, colorResource(id = R.color.mono100)) // 왼쪽 경계선 추가
                .clickable {
                    menuOpen.value = false;
                }
                .verticalScroll(sideScrollState)
            ,
            verticalArrangement = Arrangement.Top
        ) {
            // 공통 패딩 속성
            val defModifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp);
            // menus...
            Spacer(modifier = Modifier.height(32.dp))
            // 사용자 프로필
            ProfileItem(
                navController = navController,
                modifier = defModifier,
                userProfile = userProfile.value
            )

            // 작업실 = conditional or 아티스트 등록!
            if(isArtist){
                ArtistMenu(
                    navController = navController,
                    modifier = defModifier
                )
            }else {
                JoinArtistMenu(
                    navController = navController,
                    modifier = defModifier
                )
            }
            // 티켓 조회
            TicketMenu(
                navController = navController,
                modifier = defModifier
            )
            // 결제 관리
            PaymentManageMenu(
                navController = navController,
                modifier = defModifier
            )
            // 보관함
            PocketMenu(
                navController = navController,
                modifier = defModifier
            )
            // 환경 설정
            SettingMenu(
                navController = navController,
                modifier = defModifier
            )
            // 고객 센터
            CSMenu(
                navController = navController,
                modifier = defModifier
            )
        }
    }
}

// 홈 페이지 컴포저블들
@Composable
fun homePage(
    navController: NavHostController,
    modifier: Modifier,
    isArtist:Boolean = false
){
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
            .background(colorResource(id = R.color.mono100)) // temp
        ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 무한 스크롤 배너
            InfiniteLoopPager(
                list = listOf(
                    "https://thumbs.dreamstime.com/b/login-banner-template-ribbon-label-sign-177646419.jpg",
                    "https://blog.kakaocdn.net/dn/HUGVj/btrJloRg451/mctRUnHYAgTKvocX1HxXiK/img.jpg",
                    "https://as1.ftcdn.net/v2/jpg/04/86/66/48/1000_F_486664896_TxOuOR9WcKdvle5uG4kCZVnL80QyWp1t.jpg",
                    "https://img.freepik.com/free-vector/best-sale-abstract-horizontal-banner-design_1017-31300.jpg",
                    "https://png.pngtree.com/png-vector/20220530/ourmid/pngtree-photo-camera-horizontal-banner-png-image_4762429.png")
            )

            val imgSize = 64.dp
            val height = 128.dp
            // 메인 메뉴, main Menu
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // top Menu Layer
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // 내 주변 공연 찾기
                    HomeMenuBox(
                        Modifier
                            .fillMaxWidth()
                            .height(height)
                            .weight(1f)
                            .clickable { navController.navigate(PageRoutes.SearchConcert.route) },
                        menuTitle = "내 주변 공연 찾기",
                        tagName = "GPS",
                        badgeColor = colorResource(id = R.color.sky_blue400),
                        imgPainter = painterResource(id = R.drawable.icon_find_concert),
                        imgSize = imgSize
                    )
                    // 티켓 조회
                    HomeMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(height)
                            .clickable { navController.navigate(PageRoutes.TicketList.route) },
                        menuTitle = "TICKET",
                        imgPainter = painterResource(id = R.drawable.icon_ticket),
                        imgSize = imgSize,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // 아티스트 검색
                    HomeMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height)
                            .weight(1f)
                            .clickable { navController.navigate(PageRoutes.SearchArtist.route) },
                        menuTitle = "아티스트 검색",
                        imgPainter = painterResource(id = R.drawable.icon_search_artist),
                        imgSize = imgSize
                    )

                    // 아티스트 순위 조회
                    HomeMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height)
                            .weight(1f)
                            .clickable { navController.navigate(PageRoutes.ArtistRank.route) },
                        menuTitle = "ARTIST BOARD",
                        tagName = "HOT",
                        badgeColor = colorResource(id = R.color.red300),
                        imgPainter = painterResource(id = R.drawable.icon_artist_rank),
                        imgSize = imgSize
                    )

                }
                // 공연 개설하기, 아티스트인지에 따라 가변적임
                if (isArtist) {
                    HomeMenuBox(
                        Modifier
                            .fillMaxWidth()
                            .height(height)
                            // .weight(1f)
                            .clickable { navController.navigate(PageRoutes.OpenConcert.route) },
                        menuTitle = "공연 개설하기",
                        tagName = "ARTIST",
                        badgeColor = colorResource(id = R.color.red300),
                        imgPainter = painterResource(id = R.drawable.icon_create_concert),
                        imgSize = imgSize
                    );
                }
            }
        }
        // 구글 광고
        GoogleAdItem(
            navController = navController,
            height = 96.dp
        )
    }
}