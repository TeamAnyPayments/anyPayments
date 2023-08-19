package com.artist.wea.components.sidemenu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.data.ProfileInfo
import com.artist.wea.data.UserProfile
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileItem(
    navController: NavHostController,
    modifier: Modifier,
    userProfile: UserProfile
){

    Row(
        modifier = modifier.clickable {
            navController.run { navigate(PageRoutes.UserProfile.route) }
        },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GlideImage(
            imageModel = userProfile.profileURL.ifEmpty { R.drawable.icon_def_user_img },
            // Crop, Fit, Inside, FillHeight, FillWidth, None
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 100),
            // shows a placeholder ImageBitmap when loading.
            placeHolder = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
            // shows an error ImageBitmap when the request failed.
            error = ImageBitmap.imageResource(R.drawable.icon_def_user_img),
            modifier = Modifier
                .size(64.dp)
                .clip(shape = RoundedCornerShape(32.dp))
        )
        // user text information
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = userProfile.name+"님",
                style = getDefTextStyle()
            )
            Text(
                text = stringResource(R.string.text_user_greeting),
                style = getDefTextStyle()
            )
        }
    }
}