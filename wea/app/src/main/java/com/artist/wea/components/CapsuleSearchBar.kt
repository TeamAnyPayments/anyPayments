package com.artist.wea.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.getDefTextFiledStyle
import com.artist.wea.constants.getDefTextStyle
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CapsuleSearchBar(
    hintText: String = "Input Placeholder",
    searchList: Array<String>,
    inputDelay: Long = 500L,
    capacity: Int,
    isTagCapsule: Boolean = false
): SnapshotStateList<String> {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val selectList = remember { mutableStateListOf<String>() }
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val addCapsule:(element: String) -> Boolean = {
        when {
            selectList.size >= capacity -> {
                Toast.makeText(context, "장르는 ${capacity}개까지 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                false
            }
            it in selectList -> {
                Toast.makeText(context, "이미 선택된 항목입니다.", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                selectList.add(it)
                true
            }
        }
    }

    LaunchedEffect(searchText) {
        if (searchText.trim() != "") {
            delay(inputDelay)
            expanded = true
        }
    }

    Column(

    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier
                .padding(8.dp),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(),
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                colors = getDefTextFiledStyle(),
                textStyle = getDefTextStyle(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .size(24.dp)
                    )
                },
                placeholder = {
                    Text(
                        text = hintText,
                        style = getDefTextStyle().copy(
                            color = colorResource(id = R.color.mono300)
                        )
                    )
                }
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .background(colorResource(id = R.color.dark_orange50)),
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                })
            {
                var isNothing = true
                searchList.forEach {
                    if (it.startsWith(searchText.trim())) {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = {
                                expanded = !expanded
                                addCapsule(it)
                                searchText = ""
                                keyboardController?.hide()
                            }
                        )
                        isNothing = false
                    }
                }
                if (isNothing and searchText.isNotEmpty()) {
                    DropdownMenuItem(
                        text = { Text(text= "$searchText 추가하기")},
                        onClick = {
                            expanded = !expanded
                            addCapsule(searchText)
                            searchText = ""
                            keyboardController?.hide()
                        }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectList.forEach { tagText ->
                TextCapsule(
                    text = tagText,
                    randomColor = true,
                    closeButton = true,
                    onclick = {
                        selectList.remove(tagText)
                    },
                    isTagCapsule = isTagCapsule
                )
            }
        }
    }
    return selectList
}

@Preview
@Composable
fun CapsuleSearchBarPreview() {
    CapsuleSearchBar(
        searchList = arrayOf("인디 록", "인디 음악", "인디 팝", "인디 포크", "재즈", "재즈 랩", "재즈 록", "재즈 팝"),
        capacity = 3
    )
}