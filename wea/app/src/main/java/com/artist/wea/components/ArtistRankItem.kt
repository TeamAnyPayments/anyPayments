package com.artist.wea.components

import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.data.ArtistRankData
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getCardDefColors
import com.artist.wea.constants.getCardDefElevation

@Composable
fun ArtistRankItem(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    artistRankData: ArtistRankData =
        ArtistRankData(
            no = -1,
            artistName = "",
            artistAddress = "",
            artistImgURL = ""
        )
){
    Card(
        modifier= modifier.clickable {
            navController.navigate(PageRoutes.ArtistInfo.route)
        },
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
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            // artist-info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(10f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                WeaIconImage(
                    imgUrl = artistRankData.artistImgURL,
                    size = 64.dp,
                    isClip = true
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = artistRankData.artistName,
                        style = get14TextStyle()
                    )
                    Row(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Icon(
                           Icons.Filled.LocationOn,
                            contentDescription = "주소",
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.red400)
                        )

                        Text(
                            text = artistRankData.artistAddress,
                            style = get14TextStyle()
                        )
                    }
                }
            }
            Text(
                text = artistRankData.no.toString(),
                style = get14TextStyle(),
                modifier = Modifier
                    .wrapContentWidth()
                    .weight(1f)
            )
        }

    }
}
