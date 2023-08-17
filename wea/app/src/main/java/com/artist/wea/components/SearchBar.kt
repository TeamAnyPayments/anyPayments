package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getMenuItemColors

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(16.dp),
    searchOptions:Array<String> = arrayOf()
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            // modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ){
            InputForm(
                hintText = "검색어를 입력해보세요",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(6f),
            )
            Icon(
                Icons.Filled.Search,
                contentDescription = "돋보기",
                modifier = Modifier
                    .size(32.dp)
                    .weight(1f)
            )
        }

        if(searchOptions.size > 0){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                // verticalArrangement = Arrangement.
            ) {
                val sortOpt = remember { mutableStateOf(searchOptions[0]) }
                val isExpended = remember { mutableStateOf(false) }
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .defaultMinSize(minWidth = 72.dp)
                    .wrapContentHeight()
                    .padding(8.dp, 4.dp)
                    .clickable { isExpended.value = true },
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(text = sortOpt.value)
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "drop down",
                        modifier = Modifier.size(16.dp)
                    )
                    DropdownMenu(
                        modifier = Modifier
                            .wrapContentSize()
                            .background(colorResource(id = R.color.mono50)),
                        expanded = isExpended.value,
                        onDismissRequest = {isExpended.value = false},

                        ) {
                        searchOptions.forEachIndexed{idx, value ->
                            DropdownMenuItem(
                                text = {  Text(text = value) },
                                onClick = {
                                    sortOpt.value = value
                                    isExpended.value = false
                                },
                                colors = getMenuItemColors()
                            )
                        }
                    }
                }
            }
        }

    }
}
