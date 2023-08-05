package com.artist.wea.pages

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.HomeMenuBox
import com.artist.wea.components.InfiniteLoopPager
import com.artist.wea.components.sidemenu.ArtistMenu
import com.artist.wea.components.sidemenu.CSMenu
import com.artist.wea.components.sidemenu.JoinArtistMenu
import com.artist.wea.components.sidemenu.PaymentManageMenu
import com.artist.wea.components.sidemenu.PocketMenu
import com.artist.wea.components.sidemenu.ProfileItem
import com.artist.wea.components.sidemenu.SettingMenu
import com.artist.wea.components.sidemenu.TicketMenu
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val menuOpen = remember { mutableStateOf(false) } // 메뉴 상태를 기억하는 변수
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp // 디바이스 스크린 width
    val menuOffset = animateDpAsState(if (menuOpen.value) 0.dp else screenWidth, label = "") // 애니메이션 상태
    val isArtist = true; // 아티스트인지 여부를 판단할 변수

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        // TopBar Area
        Column {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 홈페이지인 경우 뒤로가기를 표현하지 않아야 함
                        if(!navController.currentDestination?.route.equals("home")){
                            // 뒤로가기 아이콘
                            Icon(
                                painter = painterResource(id = R.drawable.icon_angle_left),
                                contentDescription = "logo",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        // 제목
                        Text(
                            text = "우리동네 아티스트, WE:A",
                            style = getDefTextStyle()
                        )
                    }
                },
                // backgroundColor = AppColors.white,
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_notify),
                            contentDescription = "Alert",
                            modifier = Modifier.size(24.dp),
                             tint = colorResource(id = R.color.mono900)
                        )
                    }
                    IconButton(onClick = {
                        menuOpen.value = !menuOpen.value // 사이드 메뉴 열기
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_hambug),
                            contentDescription = "Menu",
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.mono900)
                        )
                    }
                }
            )
            homePage(navController = navController, modifier = modifier)
            }
        }
        // Side Menu Area
        Row{
            // 사이드 메뉴 여백 필터
            Box(modifier =
            Modifier
                .offset(x = menuOffset.value)
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
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
                    .weight(2f)
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
                    userName = "홍길동"
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
fun homePage(navController: NavHostController, modifier: Modifier){
    val scrollState = rememberScrollState()
    val isArtist = false;
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState)
    ) {
        InfiniteLoopPager() // 무한 스크롤 배너
        // 메인 메뉴, main Menu
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(4.dp)
        ) {
            // top Menu Layer
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
            ) {
                // 공연 개설하기, 아티스트인지에 따라 가변적임
                if (isArtist) {
                    HomeMenuBox(
                        modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(4.dp)
                            .weight(1f),
                        menuTitle = "공연 개설하기",
                        tagName = "ARTIST",
                        badgeColor = colorResource(id = R.color.red300),
                        imgPainter = painterResource(id = R.drawable.icon_create_concert)
                    );
                }
                // 내 주변 공연 찾기
                HomeMenuBox(
                    modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(4.dp)
                        .weight(1f)
                        .clickable { navController.navigate(PageRoutes.SearchConcert.route) },
                    menuTitle = "내 주변 공연 찾기",
                    tagName = "GPS",
                    badgeColor = colorResource(id = R.color.sky_blue400),
                    imgPainter = painterResource(id = R.drawable.icon_find_concert)
                );

            }

            // bottom Menu Layer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(296.dp)
            ) {
                // 아티스트 검색
                HomeMenuBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                        .clickable { navController.navigate(PageRoutes.Login.route) },
                    menuTitle = "아티스트 검색",
                    imgPainter = painterResource(id = R.drawable.icon_search_artist)
                )
                Spacer(modifier = Modifier.width(4.dp)) // 하단바 수평 간격
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(1f)
                ) {
                    // 아티스트 순위 조회
                    HomeMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(1f)
                            .clickable { navController.navigate(PageRoutes.Login.route) },
                        menuTitle = "ARTIST BOARD",
                        tagName = "HOT",
                        badgeColor = colorResource(id = R.color.red300),
                        imgPainter = painterResource(id = R.drawable.icon_artist_rank)
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // 우측 메뉴 수직 간격
                    // 티켓 조회
                    HomeMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .weight(1f)
                            .clickable { navController.navigate(PageRoutes.Login.route) },
                        menuTitle = "TICKET",
                        imgPainter = painterResource(id = R.drawable.icon_ticket)
                    )
                }
            }

        }
        Spacer(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
        )

        // 구글 광고 올 부분 todo...
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color = colorResource(id = R.color.sky_blue700))
        ) {
            Text(
                text = "광고를 준비 중입니다...",
                style = getDefTextStyle().copy(color = colorResource(id = R.color.white)),
                modifier = Modifier.padding(16.dp)
            )
        }


    }
}

/*
Box(modifier = Modifier.fillMaxSize()) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_topbar_logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(60.dp)
                                .clickable { navController.navigate(PageRoutes.OverView.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }}
                        )
                        Spacer(modifier = Modifier.width(76.dp))
                        Text(
                            text = HealthOnePage.pageTitle.value,
                            color = AppColors.mono900,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                backgroundColor = AppColors.white,
                actions = {
                    IconButton(onClick = {
                        navController.navigate(PageRoutes.Alert.route)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_bell),
                            contentDescription = "Alert",
                            modifier = Modifier.size(24.dp),
                            tint = AppColors.mono900
                        )
                    }
                    IconButton(onClick = {
                        menuOpen.value = !menuOpen.value
                    }) { // 상태를 업데이트하여 메뉴를 열고 닫습니다.
                        Icon(
                            painter = painterResource(id = R.drawable.ic_topbar_toggle),
                            contentDescription = "Menu",
                            modifier = Modifier.size(24.dp),
                            tint = AppColors.mono900
                        )
                    }
                }
            )
        }
        TopBarNavigation(navController, context)
    }
    if (menuOpen.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { menuOpen.value = false }
                )
        )
    }
    Column(
        modifier = Modifier
            .offset(x = menuOffset.value) // 애니메이션 값을 적용
            .width(272.dp)
            .fillMaxHeight()
            .background(AppColors.white)
            .border(2.dp, AppColors.mono200) // 왼쪽 경계선 추가
            .align(Alignment.TopEnd)
    ) {
        DrawerButton(
            text = ImageUri.getNicknameFromPrefs(context),
            showImage = true,
            onClick = {
                navController.navigate(PageRoutes.My.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "메인",
            icon = R.drawable.ic_home,
            iconColor = AppColors.mono700,
            onClick = {
                HealthOnePage.pageTitle.value = "메인"
                navController.navigate(PageRoutes.OverView.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "심박수",
            icon = R.drawable.ic_heart,
            iconColor = Color.Unspecified,
            onClick = {
                HealthOnePage.pageTitle.value = "심박수"
                navController.navigate(PageRoutes.HeartRate.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "식단",
            icon = R.drawable.ic_food,
            iconColor = AppColors.orange500,
            onClick = {
                HealthOnePage.pageTitle.value = "식단"
                navController.navigate(PageRoutes.MealPlan.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "수면",
            icon = R.drawable.ic_sleep,
            iconColor = AppColors.mono900,
            onClick = {
                HealthOnePage.pageTitle.value = "수면"
                navController.navigate(PageRoutes.Sleep.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "걸음수",
            icon = R.drawable.ic_walking_svg,
            iconColor = AppColors.blue900,
            onClick = {
                HealthOnePage.pageTitle.value = "걸음수"
                navController.navigate(PageRoutes.Walking.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "건강상태",
            icon = R.drawable.ic_health_info,
            iconColor = Color.Unspecified,
            onClick = {
                HealthOnePage.pageTitle.value = "건강정보"
                navController.navigate(PageRoutes.HealthStatus.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "챌린지",
            icon = R.drawable.ic_fire,
            iconColor = AppColors.red900,
            onClick = {
                HealthOnePage.pageTitle.value = "챌린지"
                navController.navigate(PageRoutes.Challenge.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
        DrawerButton(
            text = "설정",
            icon = R.drawable.ic_setting,
            iconColor = Color.Unspecified,
            onClick = {
                HealthOnePage.pageTitle.value = "설정"
                navController.navigate(PageRoutes.Setting.route)
            }
        )
        Divider(color = AppColors.mono200, thickness = 1.dp)
    }
}\
 */