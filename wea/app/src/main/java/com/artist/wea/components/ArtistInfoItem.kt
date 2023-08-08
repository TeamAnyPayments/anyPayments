package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ArtistInfo
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ArtistInfoItem(
    navController: NavHostController,
    artistInfo: ArtistInfo = ArtistInfo(),
    modifier: Modifier = Modifier
){

    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp, 8.dp)
    ){
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            GlideImage(
                imageModel = artistInfo.profileImgURL.ifEmpty { R.drawable.icon_def_user_img },
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 200),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(32.dp))
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 이름
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
        Divider(thickness = 1.dp, color = colorResource(id = R.color.mono300))
    }

}