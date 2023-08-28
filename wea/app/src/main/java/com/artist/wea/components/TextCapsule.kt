package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.util.ColorPicker

@Composable
fun TextCapsule(
    text: String = "Tag Example",
    color: Int = R.color.dark_orange100,
    randomColor: Boolean = false,
    closeButton: Boolean = true,
    onclick: () -> Unit = {},
    isTagCapsule: Boolean = false
) {
    val tagColor = remember { if (randomColor) ColorPicker.getRandomPastelColor() else color }
    Row(
        modifier = Modifier
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .background(colorResource(id = tagColor)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(PaddingValues(
                    start = 8.dp,
                )),
            text = if (isTagCapsule) "# $text" else text
        )
        if (closeButton) {
            IconButton(
                onClick = onclick
            ) {
                Icon(
                    modifier = Modifier
                        .size(16.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "")
            }
        }
    }
}

@Preview
@Composable
fun TextCapsulePreview() {
    TextCapsule()
}