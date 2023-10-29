package com.artist.wea.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artist.wea.constants.getDefTextStyle
import java.time.LocalDateTime

@Composable
fun TitleStartEndTimeForm(
    titleText: String,
    startTitle: String,
    endTitle: String
): Pair<LocalDateTime, LocalDateTime> {
    var startDateTime by remember { mutableStateOf<LocalDateTime?>(null) }
    var endDateTime by remember { mutableStateOf<LocalDateTime?>(null) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp, 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        // input 컴포넌트 제목 부분
        Text(
            text = titleText,
            style = getDefTextStyle()
        )
        Spacer(modifier = Modifier.height(16.dp))
        // DropdownTextField 로부터 값을 받고 리턴
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            startDateTime = DateTimePicker(
                modifier = Modifier
                    .weight(1f),
                titleText = "시작 예정 시간"
            )
            endDateTime = DateTimePicker(
                modifier = Modifier
                    .weight(1f),
                titleText = "종료 예정 시간"
            )
        }
    }
    return Pair(startDateTime!!, endDateTime!!)
}

@Preview
@Composable
fun TitleStartEndTimeFormPreview() {
    TitleStartEndTimeForm(
        titleText = "공연 시간",
        startTitle = "공연 시작 예정 시간",
        endTitle = "공연 종료 예정 시간"
    )
}