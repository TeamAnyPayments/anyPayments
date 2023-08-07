package com.artist.wea.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle

@Composable
fun EmailGuidText(modifier: Modifier=Modifier.padding(8.dp)){
    // guideText
    Text(
        stringResource(R.string.text_regist_final_guide),
        style = get12TextStyle().copy(color = colorResource(id = R.color.mono300)),
        modifier = modifier
    )
}