package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R

@Composable
fun UserInfoManageMenu(modifier: Modifier =
                           Modifier
                               .fillMaxWidth()
                               .wrapContentHeight()){

    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp, 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        ){

        Text(text = stringResource(R.string.text_find_id),
            style = TextStyle(
                fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.black)// to edit
            )
        )

        Text(text = stringResource(R.string.text_find_pwd),
            style = TextStyle(
                fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.black),// to edit
            )
        )


        Text(text = stringResource(R.string.text_regist),
            style = TextStyle(
                fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = colorResource(id = R.color.black),// to edit
            )
        )


    }

}