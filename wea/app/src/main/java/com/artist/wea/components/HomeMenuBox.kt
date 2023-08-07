package com.artist.wea.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.getCardDefColors
import com.artist.wea.constants.getCardDefElevation
import com.artist.wea.constants.getDefTextStyle

@Composable
fun HomeMenuBox(
    modifier: Modifier = Modifier,
    menuTitle:String = stringResource(id = R.string.empty_text),
    tagName:String = stringResource(id = R.string.empty_text),
    tagTextStyle:TextStyle = getDefTextStyle(colorResource(id = R.color.white)),
    badgeColor: Color = colorResource(id = R.color.sky_blue300),
    imgPainter:Painter = painterResource(id = R.drawable.ic_launcher_background),
    imgSize: Dp = 96.dp
){

    Card(
        modifier= modifier,
        colors = getCardDefColors(),
        elevation = getCardDefElevation(),
        shape = RoundedCornerShape(16.dp),
    ) {
        // top layer
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            //           .weight(1f)
            // .border(1.dp, color = colorResource(id = R.color.black))
        ){
            // menu text
            Text(
                text = menuTitle,
                style = getDefTextStyle().copy(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Start,
            )
            if(tagName.isNotEmpty()){
                //Spacer(modifier = modifier.height(8.dp))
                Box(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(16.dp, 0.dp)
                    .background(color = badgeColor, shape = CircleShape)
                ){
                    Text(
                        text = tagName,
                        style = tagTextStyle,
                        modifier = Modifier.padding(8.dp, 4.dp)
                    )
                }
            }
        }
        // bottom layer
        Row (modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
//            .weight(1f)
            // .border( 1.dp, color = colorResource(id = R.color.black)),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ){
            Image(
                painter = imgPainter,
                contentDescription = "concept",
                modifier = Modifier
                    .width(imgSize)
                    .height(imgSize)
                    .padding(8.dp)
            )
        }

    }


}