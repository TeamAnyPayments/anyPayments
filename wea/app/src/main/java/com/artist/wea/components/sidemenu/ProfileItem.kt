package com.artist.wea.components.sidemenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.WeaIconImage
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.data.UserProfile

// 우측 사이드 메뉴에서 사용자 프로필을 표시하기 위한 메뉴!
@Composable
fun ProfileItem(
    navController: NavHostController, // 하위 컴포저블에 네비게이션 컨트롤러를 전달하기 위해 파라미터로 받음
    modifier: Modifier,
    userProfile: UserProfile, // 사용자 프로필 데이터 클래스르 기반으로 데이터 렌더링함!
    textStyle:TextStyle = get14TextStyle() // 사용자 프로필 정보의 텍스트 스타일
){

    Row(
        modifier = modifier.clickable {
            navController.run { navigate(PageRoutes.UserProfile.route) }
        },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 사용자 프로필 이미지
        // 아이콘, 프로필 등으로 사용하기에 최적화시킨 WeaIconImage 컴포저블 재활용
        WeaIconImage(
            imgUrl = userProfile.profileURL, // 이미지 주소
            size = 56.dp, // 사이즈
            isClip = true // 이미지에 라운딩을 줄 지 여부
        )
        
        // 사용자 정보 표시 부분
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            // 유저 이름
            Text(
                text = userProfile.name+"님",
                style = textStyle
            )
            // 유저 환영 인사글
            Text(
                text = stringResource(R.string.text_user_greeting),
                style = textStyle
            )
        }
    }
}