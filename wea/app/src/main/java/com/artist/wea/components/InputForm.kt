package com.artist.wea.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.artist.wea.R
import com.artist.wea.constants.getDefTextFiledStyle
import com.artist.wea.constants.getDefTextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(
    hintText:String = stringResource(id = R.string.empty_text),
    textStyle: TextStyle = getDefTextStyle(),
    modifier: Modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
):String{
    var text by remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ){
//            OutlinedTextField(
//                value = text,
//                onValueChange = { text = it },
//                label = { Text(labelText) },
//                modifier = modifier,
//            )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            colors = getDefTextFiledStyle(),
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = hintText,
                    style = textStyle.copy(
                        color = colorResource(id = R.color.mono300)
                    )
                )
            }

        )
    }

    return text.toString();
}

