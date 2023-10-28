package com.artist.wea.screen.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues
import com.artist.wea.data.ArtistInfo

// 멤버 초대 컴포저블
@Composable
fun SeeInviteComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    memberList:List<ArtistInfo> = DummyValues().crewList // 아티스트 리스트
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

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
                    rightComposable = {
                        val resultText = "${item.userId} "+stringResource(R.string.text_cancel_invite_tail);
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "",
                            tint = colorResource(id = R.color.red300),
                            modifier = Modifier
                                .size(24.dp)
                                .weight(1f)
                                .clickable {
                                    Toast
                                        .makeText(
                                            context,
                                            resultText,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                        )
                    }
                )
            }
        }
    }
}