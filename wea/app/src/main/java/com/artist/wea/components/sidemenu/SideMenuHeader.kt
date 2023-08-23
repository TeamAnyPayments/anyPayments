package com.artist.wea.components.sidemenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle

@Composable
fun SideMenuHeader(
    korMenuText:String,
    engMenuText:String = stringResource(id = R.string.empty_text),
    modifier: Modifier = Modifier,
    korTextStyle: TextStyle = getDefTextStyle(),
    engTextStyle: TextStyle = get14TextStyle(),
    badgeName:String = stringResource(id = R.string.empty_text),
    badgeColor: Color = colorResource(id = R.color.mono100),
    badgeTextStyle:TextStyle = get12TextStyle().copy(color = colorResource(id = R.color.white))
){
    Row(modifier = modifier
        .wrapContentWidth()
        .wrapContentHeight(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = korMenuText,
            style = korTextStyle
        )
        if(engMenuText.isNotEmpty()){
            Text(
                text = engMenuText,
                style = engTextStyle
            )
        }
        if(badgeName.isNotEmpty()){
            Box(modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = badgeColor)
            ){
                Text(
                    text = badgeName,
                    style = badgeTextStyle,
                    modifier = Modifier.padding(4.dp, 2.dp)
                )
            }

        }
    }
}