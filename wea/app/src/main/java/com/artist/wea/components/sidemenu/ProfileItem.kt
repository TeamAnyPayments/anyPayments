package com.artist.wea.components.sidemenu

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle

@Composable
fun ProfileItem(
    navController: NavHostController,
    modifier: Modifier,
    userName:String = stringResource(id = R.string.empty_text),
    userImage: Painter = painterResource(id = R.drawable.icon_def_user_img)
){
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Image
        Image(
            painter = userImage,
            contentDescription = "user-img",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(32.dp)),
            contentScale = ContentScale.Fit,
        )
        // user text information
        Column(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = userName+"님",
                style = getDefTextStyle()
            )
            Text(
                text = stringResource(R.string.text_user_greeting),
                style = getDefTextStyle()
            )
        }
    }
}