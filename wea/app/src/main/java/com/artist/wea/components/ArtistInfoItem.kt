package com.artist.wea.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ArtistInfo

// 아티스트 정보 조회 시 화면 렌더링에 사용하는 컨테이너 성 컴포저블
@Composable
fun ArtistInfoItem(
    navController: NavHostController, // 하위 컴포저블에 네비게이션 컨트롤러를 전달하기 위해 파라미터로 받음
    artistInfo: ArtistInfo = ArtistInfo(), // 아티스트 정보를 표시할 때 사용할 데이터를 담은 데이터 클래스
    modifier: Modifier = Modifier,
    hasLine:Boolean = true, // 하단에 라인 표시 여부
    isActive:Boolean = true // 프로필 수정 등에서 페이지 라우팅을 막기 위한 활성화 토글 옵션
){
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable {
            if (isActive) {
                // 아티스트의 id 정보를 기반으로 페이지를 별개로 라우팅함.
                navController.navigate(PageRoutes.ArtistInfo.route+"/${artistInfo.userId}")
            }
        }
    ){
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            // 아티스트 프로필 사진 표시!
            // 아이콘, 프로필 렌더링에 최적화시킨 WeaIconImage 컴포저블 재활용
            WeaIconImage(
                imgUrl = artistInfo.profileImgURL,
                size = 64.dp,
                isClip = true
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 아티스트 명
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "artist-icon",
                        modifier = Modifier.size(24.dp),
                        tint = colorResource(id = R.color.mono900)
                    )
                    Text(
                        text = artistInfo.artistName,
                        style = getDefTextStyle()
                            .copy(color = colorResource(id = R.color.mono900))
                    )

                }
                // 한줄소개
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        contentDescription = "artist-icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        text = artistInfo.comment,
                        style = getDefTextStyle()
                            .copy(color = colorResource(id = R.color.mono900))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // 라인이 있을 경우 라인을 표시!
        if(hasLine){
            Divider(thickness = 1.dp, color = colorResource(id = R.color.mono300))
        }
    }

}