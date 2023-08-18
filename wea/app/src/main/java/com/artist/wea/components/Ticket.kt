package com.artist.wea.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artist.wea.R

@Composable
fun Ticket() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.mono500),
                shape = RoundedCornerShape(16.dp)

            )
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
                    .background(Color.Red)
            ) {
                Text(text = "영역 1")
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Blue)
            ) {
                Text(text = "영역 2")
            }
        }
    }
}

@Preview
@Composable
fun TicketPreview() {
    Ticket()
}