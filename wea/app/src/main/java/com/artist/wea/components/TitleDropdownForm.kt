package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.constants.getDefTextStyle

@Composable
fun TitleDropdownForm(
    titleText:String,
    innerLabel:String,
    listItems:Array<String>
):String{

    var selectedItem by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 8.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.Start
    ) {

        // input 컴포넌트 제목 부분
        Text(
            text = titleText,
            style = getDefTextStyle()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // DropdownTextField 로부터 값을 받고 리턴
        selectedItem = DropdownTextField(
            listItems = listItems,
            innerLabel = innerLabel
        )
    }
    return selectedItem
}