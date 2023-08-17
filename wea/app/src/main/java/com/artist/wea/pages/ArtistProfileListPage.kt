package com.artist.wea.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.Badge
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.ProfileItem
import com.artist.wea.components.uidtclass.SearchArtistInfo

@Composable
fun ArtistProfileListPage(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTopBar(
            navController = navController,
            pageTitle = "아티스트 프로필 편집"
        )

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
            memberList.forEachIndexed{
                    idx, item ->
                ProfileItem(
                    navController = navController,
                    content = item,
                    rightComposable = {
//                        Icon(
//                            Icons.Filled.Send,
//                            contentDescription = "",
//                            tint = colorResource(id = R.color.sky_blue300),
//                            modifier = Modifier.size(24.dp).weight(1f).clickable {
//                                Toast.makeText(context, "${item.userId} 님에게 초대를 보냈습니다.", Toast.LENGTH_SHORT).show()
//                            }
//                        )
                        Badge(
                            text = if(idx==0) "리더" else "멤버",
                            color = if(idx==0) colorResource(id = R.color.sky_blue300)
                                    else colorResource(id = R.color.pastel_purple100),
                            txtColor = colorResource(id = R.color.white),
                        )

                    },
                )
            }
        }
    }

}