package com.artist.wea.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(
    titleText:String = stringResource(R.string.label_text),
    modifier: Modifier = Modifier
        .border(width = 1.dp, color = colorResource(id = R.color.mono600)) // to edit
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(start = 8.dp, top = 8.dp, bottom = 12.dp)
):String{
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()){
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(titleText) },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
    }

    return text.toString();
}

