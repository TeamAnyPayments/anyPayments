package com.artist.wea.pages

import android.app.Activity
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.ShowProfileDialog
import com.artist.wea.components.WeaIconImage
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.UserProfile
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.JSONParser
import com.artist.wea.util.PhotoSelector
import com.artist.wea.util.PreferenceUtil
import org.json.JSONObject


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
                    Toast.makeText(context, "이미지를 불러오던 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(context, "회원 정보가 만료되었습니다.", Toast.LENGTH_SHORT).show()
//                navController.navigate(PageRoutes.Login.route){
//                    popUpTo(0)
//                }
            }
        })
    }else {
        Log.d("PROFILE_PAGE:::", "캐싱 >>> ${profileJson.value}")

        val json = JSONObject(profileJson.value)
        userProfile.value = jParser.parseJsonToUserProfile(json)
    }

    val modalVisibleState = remember { mutableStateOf(false) }
    ShowProfileDialog(
        visible = modalVisibleState.value,
        defaultImageURL = userProfile.value.profileURL,
        localImgBitmap = profileBitmap.value,
        onDismissRequest = {
            modalVisibleState.value = false
        })


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
                                if(prefs.clearAll()){
                                    // TODO.. prefs를 통해 토큰 값 꺼내서 바인딩, 서버와 통신 후 로그아웃 처리
                                    Toast.makeText(context, "로그아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                                    val tokenMap= mapOf(Pair("token", "")) // 현재 사용중인 토큰
                                    viewModel.logout(tokenMap);

                                    viewModel.loginUserRes.observe(mOwner, Observer {
                                        Log.d("LOGOUT_RESULT....", "${it.toString()}")
                                    })
                                    navController.navigate(PageRoutes.Login.route){
                                        popUpTo(0)
                                    }
                                }

                            }
                        )
                        // 로그아웃
                        Text(
                            text = stringResource(R.string.text_menu_user_quit),
                            style = get14TextStyle()
                                .copy(
                                    color = colorResource(id = R.color.red500)
                                ),
                            modifier = Modifier.clickable {
                                navController.navigate(PageRoutes.UserQuit.route)
                            }
                        )
                    }
                }
            )
        }
   }
}