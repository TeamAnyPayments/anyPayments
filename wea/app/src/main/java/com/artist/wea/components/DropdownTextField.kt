package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.artist.wea.R
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getOutlinedTextFieldColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownTextField(
    listItems: Array<String>,
    innerLabel: String
): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(listItems[0]) }

    Box(
       modifier = Modifier
           .fillMaxWidth()
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = innerLabel) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                modifier = Modifier
                    .menuAnchor(),
                colors = getOutlinedTextFieldColors(),
                textStyle = get14TextStyle()
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .background(colorResource(id = R.color.dark_orange50)),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                listItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectedOption) },
                        onClick = {
                            selectedItem = selectedOption
                            expanded = !expanded
                        }
                    )
                }
            }
        }
    }

    return selectedItem
}