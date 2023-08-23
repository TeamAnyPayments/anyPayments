package com.artist.wea.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.LargeButton
import com.artist.wea.components.MemberItem
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.uidtclass.SearchArtistInfo
import com.artist.wea.constants.PageRoutes
import com.artist.wea.constants.getBtnColorByIdx
import com.artist.wea.constants.getMenuItemColors

@Composable
fun MemberManagePage(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.mono50)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        PageTopBar (
            navController = navController,
            pageTitle = "멤버 관리",
            singleIcon = Icons.Filled.Add,
            rightMenuText = "저장",
            rightMenuAction = {
                // navController.navigate(PageRoutes.MemberAdd.route)
                Toast.makeText(context, "변경 사항이 저장 되었습니다", Toast.LENGTH_SHORT).show()
            }
        )
        // 멤버 관리
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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

            val searchOptions = listOf("리더", "멤버") // TODO.. 백엔드 DTO와 함께 분류 구분해야함
            memberList.forEach {
                    item ->
                MemberItem(
                    navController = navController,
                    content = item,
                    rightComposable =
                    {
                        if(searchOptions.size > 0){
                            Column(
                                modifier = Modifier.wrapContentWidth(),
                                horizontalAlignment = Alignment.End,
                                // verticalArrangement = Arrangement.
                            ) {
                                val sortOpt = remember { mutableStateOf(searchOptions[0]) }
                                val isExpended = remember { mutableStateOf(false) }
                                Row(modifier = Modifier
                                    .wrapContentWidth()
                                    .defaultMinSize(minWidth = 72.dp)
                                    .wrapContentHeight()
                                    .padding(8.dp, 4.dp)
                                    .clickable { isExpended.value = true },
                                    horizontalArrangement = Arrangement.Center
                                ){
                                    Text(text = sortOpt.value)
                                    Icon(
                                        Icons.Filled.ArrowDropDown,
                                        contentDescription = "drop down",
                                        modifier = Modifier.size(16.dp)
                                    )
                                    DropdownMenu(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .background(colorResource(id = R.color.mono50)),
                                        expanded = isExpended.value,
                                        onDismissRequest = {isExpended.value = false},

                                        ) {
                                        searchOptions.forEachIndexed{idx, value ->
                                            DropdownMenuItem(
                                                text = {  Text(text = value) },
                                                onClick = {
                                                    sortOpt.value = value
                                                    isExpended.value = false
                                                },
                                                colors = getMenuItemColors()
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
            // 새로운 멤버 초대
            LargeButton(
                btnText = "새로운 멤버 초대",
                btnColors = getBtnColorByIdx(3),
                buttonAction = {
                    navController.navigate(PageRoutes.MemberAdd.route)
                }
            )

        }
    }
}
