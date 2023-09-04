package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.constants.getRadioButtonColors

@Composable
fun MultipleRadioButtons(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    selectOptions:List<String> = listOf()
):String {
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }
    val currentIndex = remember { mutableStateOf(-1) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            text = "탈퇴 사유 [필수]",
            style = getDefTextStyle()
                .copy(
                    fontWeight = FontWeight.Bold
                )
        )
        // Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
        selectOptions.forEach { item ->
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) },
                        role = Role.RadioButton
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null,
                    colors = getRadioButtonColors()
                )
                Text(
                    text = item,
                    modifier = Modifier.wrapContentWidth()
                )
            }
        }

        if(
            selectedValue.value == selectOptions[selectOptions.size-1]
        ){
            val other = InputForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 16.dp, end = 16.dp),
                hintText = stringResource(R.string.text_other_reason)
            )
        }
    }

    return selectedValue.value
}