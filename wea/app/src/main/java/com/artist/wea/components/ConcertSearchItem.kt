package com.artist.wea.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.data.ConcertListInfo

@Composable
fun ConcertSearchItem(
    navController: NavHostController,
    content: ConcertListInfo = ConcertListInfo(),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(shape = RoundedCornerShape(4.dp))
        .background(color = colorResource(id = R.color.mono50))
        .padding(8.dp, 4.dp),
    isActive:Boolean = true,
){
    val context = LocalContext.current

    Box(
        modifier = modifier.clickable {
            if(isActive){
                navController.navigate(PageRoutes.ConcertInfo.route+"/${content.concertId}")
            }else {
                Toast.makeText(context, "공연 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
                Log.d(
                    "REJECT_MOVE_PAGE::", "페이지 이동할 수 없음 : ${"${content.concertId} | ${DummyValues().defConcertInfo.concertId}"}"
                )
            }
        }
    ){
        Row(modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            WeaIconImage(
                imgUrl = content.imgURL,
                size = 72.dp,
                isClip = false,
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