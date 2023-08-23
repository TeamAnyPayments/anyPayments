package com.artist.wea.pages

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.PaymentItem
import com.artist.wea.data.PaymentInfo

@Composable
fun PaymentsManagePage(
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
            pageTitle = "결제수단 관리"
        )

        val context = LocalContext.current;
        val scrollState = rememberScrollState()
        val paymentInfoList = listOf<PaymentInfo>(
            PaymentInfo(
                name = "카카오 페이",
                paymentImgURL = R.drawable.kakaotalk_sharing_btn_medium
            ),
            PaymentInfo(
                name = "네이버 페이",
                paymentImgURL = R.drawable.icon_naver
            ),
        )

        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "간편결제 ${paymentInfoList.size} 개",
            rightMenuIcon = Icons.Filled.AddCircle,
            rightMenuAction = {
                Toast.makeText(context, "간편결제 등록...", Toast.LENGTH_SHORT).show()
            },
            screen = {
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(scrollState)
                        //.padding(8.dp , 4.dp),
                    ,verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    paymentInfoList.forEach {item ->
                            PaymentItem(
                                name = item.name,
                                paymentImgURL = item.paymentImgURL
                            )
                    }

                }
            }
        )
    }
}