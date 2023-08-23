package com.artist.wea.components.sidemenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun SideMenuHeader(
    korMenuText:String,
    engMenuText:String = stringResource(id = R.string.empty_text),
    modifier: Modifier = Modifier,
    badgeName:String = stringResource(id = R.string.empty_text),
    badgeColor: Color = colorResource(id = R.color.mono100),
    badgeTextStyle:TextStyle = get12TextStyle().copy(color = colorResource(id = R.color.white))
){
    Row(modifier = modifier
        .wrapContentWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.Bottom
    ){
        Text(
            text = korMenuText,
            style = getDefTextStyle().copy(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        if(engMenuText.isNotEmpty()){
            Text(
                text = engMenuText,
                style = get14TextStyle()
            )
        }
        if(badgeName.isNotEmpty()){
            Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(16.dp, 0.dp)
                .background(color = badgeColor, shape = CircleShape)
            ){
                Text(
                    text = badgeName,
                    style = badgeTextStyle,
                    modifier = Modifier.padding(8.dp, 4.dp)
                )
            }

        }
    }
}