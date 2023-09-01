package com.artist.wea.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.data.ConcertInfo
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get14TextStyle

@Composable
fun MemberItem(
    navController: NavHostController,
    content: ConcertInfo = ConcertInfo(),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 4.dp),
    isActive:Boolean = true,
    rightComposable: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.clickable {
            if (isActive) {
                navController.navigate(PageRoutes.ArtistInfo.route)
            }
        }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            WeaIconImage(
                imgUrl = content.imgURL,
                size = 64.dp,
                isClip = true
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .weight(5f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = content.userId, style = get14TextStyle())
                    Text(text = content.email, style = get14TextStyle())
                }
                rightComposable()
            }
        }
    }
}
