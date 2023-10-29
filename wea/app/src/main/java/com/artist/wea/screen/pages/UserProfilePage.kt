package com.artist.wea.screen.pages

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.screen.components.InfoUnit
import com.artist.wea.screen.components.LogOutAlert
import com.artist.wea.screen.components.PageTopBar
import com.artist.wea.screen.components.ShowProfileDialog
import com.artist.wea.screen.components.WeaIconImage
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.UserProfile
import com.artist.wea.network.instance.Retrofit
import com.artist.wea.network.model.RegisterViewModel
import com.artist.wea.network.repository.RegisterRepository
import com.artist.wea.util.JSONParser
import com.artist.wea.util.PhotoSelector
import com.artist.wea.util.PreferenceUtil
import com.artist.wea.util.ToastManager.Companion.shortToast
import org.json.JSONObject

// 사용자 프로필 페이지
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserProfilePage(
    navController: NavHostController
){

    // 비동기 통신을 위한 기본 객체 settings
    val context = LocalContext.current;
    val mOwner = LocalLifecycleOwner.current
    val repository = RegisterRepository()
    val viewModel = RegisterViewModel(repository)

    val profileBitmap = remember { mutableStateOf<Bitmap?>(null) } //
    // 사진 불러오기 기능
    val photoSelector = PhotoSelector()
    val takePhotoFromAlbumLauncher = // 갤러리에서 사진 가져오기
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    // Log.d("IMAGE:::", "${uri.toString()}")
                    photoSelector.setImageToVariable(
                        context = context,
                        uri = uri,
                        imageSource = profileBitmap,
                        fileName = "user_profile"
                    )
                } ?: run {
                    shortToast(context,
                        text = "이미지를 불러오던 중 오류가 발생했습니다.")
                }
            } else if (result.resultCode != Activity.RESULT_CANCELED) {
                // ??
            }
        }

    // 사용자 정보를 불러오기 위한 prefs 및 관련 변수들
    val prefs = PreferenceUtil(context);
    val profileJson = remember{
        mutableStateOf(prefs.getString("profile_json", ""))
    }
    val userProfile = remember { mutableStateOf(UserProfile()) }
    val jParser = JSONParser() // json parser

    // 프로필 페이지 렌더링 시 사용자 정보를 가져옴
    if(profileJson.value.isEmpty()){
        viewModel.getUserInfo()
        viewModel.getUserInfoRes.observe(mOwner, Observer {
            if(it != null ){
                Log.d("PROFILE_PAGE:::", "${it.toString()}")
                val jsonString = it.toString()
                prefs.setString("profile_json", "$jsonString") // prefs 저장
                profileJson.value = jsonString // 현재 상태에도 저장
                userProfile.value = jParser.parseJsonToUserProfile(it)
                Log.d("PROFILE_PAGE:::", "서버 >>> ${profileJson.value}")

            }else {
                Log.d("PROFILE_PAGE:::", "토큰 만료")
                shortToast(context, text = "회원 정보가 만료되었습니다.")
                navController.navigate(PageRoutes.Login.route){
                    popUpTo(0)
                }
            }
        })
    }else {
        Log.d("PROFILE_PAGE:::", "캐싱 >>> ${profileJson.value}")

        val json = JSONObject(profileJson.value)
        userProfile.value = jParser.parseJsonToUserProfile(json)
    }

    // 사용자 프로필 페이지에서 이미지를 미리 보기를 위한 커스텀 다이얼로그 창
    val modalVisibleState = remember { mutableStateOf(false) } // 표시 여부 결정할 변수
    ShowProfileDialog( // 사용자 이미지 다이얼로그
        visible = modalVisibleState.value,
        defaultImageURL = userProfile.value.profileURL,
        localImgBitmap = profileBitmap.value,
        onDismissRequest = {
            modalVisibleState.value = false
        })

    // 로그아웃 시 사용자에게 표시할 경고 창
    val logOutVisibleState = remember { mutableStateOf(false) }
    LogOutAlert(
        visible = logOutVisibleState.value,
        onDismissRequest = {
            logOutVisibleState.value = false
        },
        logOutAction = {
            // 사용자 로그아웃 처리는 모달창에서 수행!
            userLogOut(
                context = context,
                navController = navController,
                prefs = prefs,
                viewModel = viewModel,
                mOwner = mOwner,
                logOutVisibleState = logOutVisibleState
            )
        }
    )

    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageTopBar(
            navController = navController,
            pageTitle = stringResource(R.string.text_pgname_user_profile)
        )

        // 사용자 프로필 유닛
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            WeaIconImage(
                imgUrl = userProfile.value.profileURL,
                size = 144.dp,
                bitmap = profileBitmap.value,
                isClip = true,
                modifier = Modifier.combinedClickable(
                    onClick = {
                        modalVisibleState.value = true
                    },
                    onLongClick = {
                        takePhotoFromAlbumLauncher.launch(photoSelector.takePhotoFromAlbumIntent)
                    },
                )
            )

            // 유저 이름
            Text(
                text = userProfile.value.name,
                style = getDefTextStyle()
            )
            // 유저 아이디
            Text(
                text = userProfile.value.userId,
                style = get14TextStyle()
            )
            // 유저 이메일
            Text(
                text = userProfile.value.email,
                style = get14TextStyle()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        // 내 정보 관리
        Column(modifier = Modifier
            .padding(16.dp , 12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // 나의 정보 관리
            InfoUnit(
                titleText = stringResource(R.string.text_title_profile_menu),
                titleTextStyle = getDefTextStyle(),
                screen = {
                    Column(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(8.dp, 4.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        // 이메일 변경
                        Text(
                            text = stringResource(R.string.text_menu_change_email),
                            style = get14TextStyle(),
                            modifier = Modifier.clickable {
                                navController.navigate(PageRoutes.ChangeEmail.route)
                            }
                        )
                        // 비밀번호 변경
                        Text(
                            text = stringResource(R.string.text_menu_change_pwd),
                            style = get14TextStyle(),
                            modifier = Modifier.clickable {
                                navController.navigate(PageRoutes.ChangePwd.route)
                            }
                        )
                        // 소셜 계정 관리
                        Text(
                            text = stringResource(R.string.text_menu_edit_social_account),
                            style = get14TextStyle(),
                            modifier = Modifier
                        )
                        // 로그아웃
                        Text(
                            text = stringResource(R.string.text_menu_logout),
                            style = get14TextStyle(),
                            modifier = Modifier.clickable {
                                logOutVisibleState.value = true // 로그아웃 모달창 ON
                            }
                        )
                        // 회원 탈퇴
                        Text(
                            text = stringResource(R.string.text_menu_user_quit),
                            style = get14TextStyle()
                                .copy(
                                    color = colorResource(id = R.color.red500)
                                ),
                            modifier = Modifier.clickable {
                                // 회원 탈퇴는 회원 탈퇴 양식을 작성하도록 페이지 이동
                                navController.navigate(PageRoutes.UserQuit.route)
                            }
                        )
                    }
                }
            )
        }
   }
}

// 사용자 로그아웃 함수
fun userLogOut(
    context:Context, // context
    navController: NavHostController,
    prefs:PreferenceUtil, // prefs Util..
    viewModel: RegisterViewModel, // 로그아웃 처리를 담당할 뷰모델
    mOwner:LifecycleOwner, // 뷰모델의 값 변환을 감지할 라이플 사이클 오너
    logOutVisibleState:MutableState<Boolean> // 로그아웃 성공 시 모달창을 함께 종료하기 위해, 노출 여부를 관리할 MutableState를 가져온다.
){
    val lgTag = "LOGOUT_ACTION:::"
    val tokenMap= mapOf(Pair("token", prefs.getString("token", ""))) // 현재 사용중인 토큰 정보를 꺼냄
    Log.d(lgTag, "로그아웃 시도 >> $tokenMap")

    if(prefs.clearAll()){ // SharedPreference에서 관리중이던 모든 정보를 초기화 시킴
        Log.d(lgTag, "prefs 초기화 시도");
        Log.d(lgTag, "값 점검 [token] : ${prefs.getString("token", "null")}")
        Log.d(lgTag, "값 점검 [profile_json] : ${prefs.getString("profile_json", "null")}")

        // prefs를 통해 토큰 값 꺼내서 바인딩, 서버와 통신 후 로그아웃 처리
        viewModel.logout(tokenMap);
        viewModel.logoutRes.observe(mOwner, Observer {
            Log.d(lgTag, "서버 로그아웃 시도 >> ${it.toString()}")
            if(!it){ // 서버와의 로그아웃 시도가 정상적으로 이루어졌을 경우, 최종 로그아웃 처리
                Retrofit.token.value = "" // 초기화
                logOutVisibleState.value = false;
                shortToast(context, text = context.getString(R.string.text_logout_ok)) // 로그아웃 toast 알림
                navController.navigate(PageRoutes.Login.route){
                    popUpTo(0)
                }
            }
        })

    }
}