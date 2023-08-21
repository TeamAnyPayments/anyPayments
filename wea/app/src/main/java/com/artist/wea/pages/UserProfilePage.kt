package com.artist.wea.pages

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.UserProfile
import com.artist.wea.model.RegisterViewModel
import com.artist.wea.repository.RegisterRepository
import com.artist.wea.util.JSONParser
import com.artist.wea.util.PreferenceUtil
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import okhttp3.Route
import org.json.JSONObject


@Composable
fun UserProfilePage(
    navController: NavHostController
){

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PageTopBar(
            navController = navController,
            pageTitle = "내 프로필"
        )

        // 사용자 프로필 유닛
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            GlideImage(
                imageModel = userProfile.value.profileURL.ifEmpty { R.drawable.icon_def_user_img },
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 100),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                modifier = Modifier
                    .size(156.dp)
                    .clip(shape = RoundedCornerShape(78.dp))
            )

            // 유저 이름
            Text(
                text = userProfile.value.name,
                style = getDefTextStyle().copy(fontSize = 20.sp)
            )
            // 유저 아이디
            Text(
                text = userProfile.value.userId,
                style = getDefTextStyle()
            )
            // 유저 이메일
            Text(
                text = userProfile.value.email,
                style = getDefTextStyle()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        // 내 정보 관리
        Column(modifier = Modifier
            .padding(16.dp , 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // 나의 정보 관리
            InfoUnit(
                titleText = "나의 정보 관리",
                screen = {}
            )
            // 이메일 변경
            Text(
                text = "이메일 변경",
                style = getDefTextStyle(),
                modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.ChangeEmail.route)
                }
            )
            // 비밀번호 변경
            Text(
                text = "비밀번호 변경",
                style = getDefTextStyle(),
                modifier = Modifier.clickable {
                    navController.navigate(PageRoutes.ChangePwd.route)
                }
            )
            // 소셜 계정 관리
            Text(
                text = "소셜 계정 관리",
                style = getDefTextStyle(),
                modifier = Modifier
            )
            // 로그아웃
            Text(
                text = "로그아웃",
                style = getDefTextStyle(),
                modifier = Modifier.clickable {
                    if(prefs.clearAll()){
                        Toast.makeText(context, "로그아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                        viewModel.logout()
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
                text = "회원탈퇴",
                style = getDefTextStyle()
                    .copy(
                        color = colorResource(id = R.color.red500)
                    ),
                modifier = Modifier
            )
        }


    }
}