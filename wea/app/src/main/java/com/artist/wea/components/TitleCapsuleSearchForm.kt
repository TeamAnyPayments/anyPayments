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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.artist.wea.constants.getDefTextStyle

@Composable
fun TitleCapsuleSearchForm(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    titleText: String = "Title Text",
    hintText: String = "Input Placeholder",
    searchList: Array<String>,
    inputDelay: Long = 500L,
    capacity: Int,
    isTagCapsule: Boolean = false
): SnapshotStateList<String> {
    var selectList = remember { mutableStateListOf<String>() }
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = titleText,
            style = getDefTextStyle()
        )
        Spacer(modifier = Modifier.height(16.dp))
        selectList = CapsuleSearchBar(
            hintText = hintText,
            searchList = searchList,
            inputDelay = inputDelay,
            capacity = capacity,
            isTagCapsule = isTagCapsule
        )

    }
    return selectList
}