package com.artist.wea.components.sidemenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun PaymentManageMenu(
    navController: NavHostController,
    modifier: Modifier,
    korTextStyle: TextStyle = getDefTextStyle(),
    engTextStyle: TextStyle = get14TextStyle(),
    menuTextStyle: TextStyle = get14TextStyle()
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        val menuModifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 4.dp)

        SideMenuHeader(
            korMenuText = "결제 관리",
            engMenuText = "Payments",
            korTextStyle = korTextStyle,
            engTextStyle = engTextStyle
        )
//        // Options...
//        Text(
//            text = "결제수단 등록/삭제",
//            style = menuTextStyle,
//            modifier = menuModifier
//                .clickable {
//                    navController.navigate(PageRoutes.PaymentsManage.route)
//                }
//        )
    }

}
