package com.artist.wea.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.artist.wea.R
import com.artist.wea.constants.PageRoutes

@Composable
fun LoginButton(
    navController: NavController,
    buttonText:String = stringResource(R.string.empty_text),
    isIcon:Boolean = false,
    imgPainter:Painter = painterResource(id = R.drawable.ic_launcher_foreground),
    bgColor:Color = colorResource(id = R.color.dark_orange300), // to edit
    textColor:Color = colorResource(id = R.color.brown900), // to edit
    modifier: Modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = bgColor,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .clickable { navController.navigate(PageRoutes.Home.route) }
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        if(isIcon){
            Image(
                painter = imgPainter,
                contentDescription = stringResource(R.string.login_icon_desc_text),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp)
            )
        }else {
            Spacer(modifier = Modifier
                .width(36.dp)
                .height(36.dp))
        }

        Text(text = buttonText,
            style = TextStyle(
                fontSize = 20.sp,
                // fontFamily = FontFamily(Font(R.font.inter)),
                fontWeight = FontWeight(400),
                color = textColor, // edit
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .width(36.dp)
            .height(36.dp))

    }}