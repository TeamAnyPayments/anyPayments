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
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.R
import com.artist.wea.components.InfoUnit
import com.artist.wea.components.PageTopBar
import com.artist.wea.components.PaymentItem
import com.artist.wea.constants.DummyValues
import com.artist.wea.constants.getDefTextStyle

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
            pageTitle = stringResource(R.string.text_title_payments_manage)
        )

        val context = LocalContext.current;
        val scrollState = rememberScrollState()
        val paymentInfoList = DummyValues().paymentInfoList

        InfoUnit(
            modifier = Modifier.padding(16.dp, 12.dp),
            titleText = "간편결제 ${paymentInfoList.size} 개",
            titleTextStyle = getDefTextStyle(),
            rightMenuIcon = Icons.Rounded.AddCircle,
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