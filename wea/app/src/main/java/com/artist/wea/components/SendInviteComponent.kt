package com.artist.wea.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.uidtclass.SearchArtistInfo

@Composable
fun SendInviteComponent(navController: NavHostController,
                        modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchBar()
        val memberList = arrayOf<SearchArtistInfo>(
            SearchArtistInfo(
                imgURL = "https://www.pinkvilla.com/images/2023-01/167366856_ariana-grande-1_1280*720.jpg",
                userId = "tired_cat",
                email = "tired_cat@naver.com"
            ),
            SearchArtistInfo(
                imgURL = "https://ichef.bbci.co.uk/news/1536/cpsprodpb/98B4/production/_130129093_winner.png",
                userId = "swag_cat",
                email = "swag_cat@gmail.com"
            ),
            SearchArtistInfo(
                imgURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8jKhRZVaPp-lAxLEiHwl7CBosQM1G2HXrqA&usqp=CAU",
                userId = "sad_cat",
                email = "sad_cat@daum.net"
            )
        )

        val context = LocalContext.current
        // list..
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            memberList.forEach {
                    item ->
                MemberItem(
                    navController = navController,
                    content = item,
                    rightIcon = Icons.Filled.Send,
                    iconColor = colorResource(id = R.color.sky_blue300),
                    rightMenuAction = {
                        Toast.makeText(context, "${item.userId} 님에게 초대를 전송했습니다.", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
