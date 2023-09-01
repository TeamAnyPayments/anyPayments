package com.artist.wea.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.data.ConcertInfo

@Composable
fun ProfileItem(
    navController: NavHostController,
    content: ConcertInfo = ConcertInfo(),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 4.dp),
    isActive:Boolean = true,
    destination:()->Unit = {navController.navigate(PageRoutes.MemberManage.route)},
    rightComposable: @Composable () -> Unit,
    memberCnt:Int = 1
) {
    Box(
        modifier = modifier.clickable {
            if (isActive) {
                destination()
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
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "",
                            modifier = Modifier.size(20.dp),
                            tint = colorResource(id = R.color.mono300)
                        )
                        Text(
                            text = memberCnt.toString(),
                            style = get12TextStyle()
                                .copy(
                                    color = colorResource(id = R.color.mono300)
                                )
                        )
                    }
                }
                rightComposable()
            }
        }
    }
}
