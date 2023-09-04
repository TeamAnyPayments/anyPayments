package com.artist.wea.components

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
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.constants.DummyValues

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
        val memberList = DummyValues().crewList

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
                        Icon(
                            Icons.Filled.Send,
                            contentDescription = "",
                            tint = colorResource(id = R.color.sky_blue300),
                            modifier = Modifier.size(24.dp).weight(1f).clickable {
                                Toast.makeText(context, "${item.userId} 님에게 초대를 보냈습니다.", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }
        }
    }
}
