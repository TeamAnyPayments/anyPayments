package com.artist.wea.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.artist.wea.R
import com.artist.wea.constants.getDefTextFiledStyle
import com.artist.wea.constants.getDefTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyForm(
    hintText:String = stringResource(id = R.string.empty_text),
    textStyle: TextStyle = getDefTextStyle(),
    modifier: Modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
):String{
    var text by remember { mutableStateOf(hintText) }
    Column(
        modifier = modifier
    ){
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            colors = getDefTextFiledStyle(),
            textStyle = textStyle,
        )
    }

    return text.toString();
}

