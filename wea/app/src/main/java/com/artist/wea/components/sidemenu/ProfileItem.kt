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

@Composable
fun ProfileItem(
    navController: NavHostController,
    modifier: Modifier,
    userProfile: UserProfile,
    textStyle:TextStyle = get14TextStyle()
){

    Row(
        modifier = modifier.clickable {
            navController.run { navigate(PageRoutes.UserProfile.route) }
        },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        WeaIconImage(
            imgUrl = userProfile.profileURL,
            size = 56.dp,
            isClip = true
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
                style = textStyle
            )
            Text(
                text = stringResource(R.string.text_user_greeting),
                style = textStyle
            )
        }
    }
}