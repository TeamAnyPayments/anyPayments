package com.artist.wea.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getCardDefColors
import com.artist.wea.constants.getCardDefElevation
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ArtistRankItem(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    no:Int = 1,
    artistName:String = stringResource(id = R.string.empty_text), // 아티스트 이름
    artistAddress:String = stringResource(id = R.string.empty_text), // 활동 주소
    artistImg:Painter // 아티스트 이미지
){
    Card(
        modifier= modifier,
        colors = getCardDefColors(),
        elevation = getCardDefElevation(dpSize = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(
                text = no.toString(),
                style = get14TextStyle(),
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
            // artist-info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(10f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                // artist-text-info
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(3f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = artistName,
                        style = getDefTextStyle().copy(fontSize = 20.sp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    // 주소지
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Rounded.Place,
                            "place"
                        )
                        Text(
                            text = artistAddress,
                            style = getDefTextStyle(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
                //artist-graphic-info
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 가수 이미지
                    Image(
                        painter = artistImg,
                        contentDescription = "img",
                        modifier = Modifier
                            .size(72.dp)
                            .clip(shape = RoundedCornerShape(36.dp)),
                        contentScale = ContentScale.Crop
                    )
                    // 프로필 보기
                    SmallButton(
                        navController = navController,
                        btnText = "프로필 보기",
                        btnColor = colorResource(id = R.color.pastel_purple100),
                    )
                }
            }
        }

    }
}
