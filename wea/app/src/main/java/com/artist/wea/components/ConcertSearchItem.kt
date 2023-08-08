package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
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
import com.artist.wea.components.uidtclass.SearchArtistInfo
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ConcertSearchItem(
    navController: NavHostController,
    content: SearchArtistInfo = SearchArtistInfo(),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(shape = RoundedCornerShape(4.dp))
        .background(color = colorResource(id = R.color.mono50))
        .padding(8.dp, 4.dp)
){
    Box(
        modifier = modifier.clickable {
            navController.navigate(PageRoutes.ConcertInfo.route)
        }
    ){
        Row(modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            GlideImage(
                imageModel = content.imgURL.ifEmpty { R.drawable.icon_def_user_img },
                // Crop, Fit, Inside, FillHeight, FillWidth, None
                contentScale = ContentScale.Crop,
                // shows an image with a circular revealed animation.
                circularReveal = CircularReveal(duration = 250),
                // shows a placeholder ImageBitmap when loading.
                placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                // shows an error ImageBitmap when the request failed.
                error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
                modifier = Modifier.size(72.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = content.artistName, style = get14TextStyle())
                Text(text = content.concertIntroduce, style = get14TextStyle())
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        Icons.Filled.Place,
                        contentDescription = "location",
                        tint = colorResource(id = R.color.mono800)
                    )
                    Text(
                        text = content.location,
                        style = get12TextStyle()
                    )
                }
            }
        }
    }
}