package com.artist.wea.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimePicker(
    modifier: Modifier = Modifier,
    titleText: String
):LocalDateTime {
    var timeState by remember {
        mutableStateOf<LocalTime>(LocalTime.now())
    }
    var dateState by remember {
        mutableStateOf<LocalDate>(LocalDate.now())
    }

    val context = LocalContext.current
    val timePicker = TimePickerDialog(
        context, { _, hourOfDay, minute ->
            timeState = LocalTime.of(hourOfDay, minute)
            if (LocalDateTime.of(dateState, timeState) < LocalDateTime.now()) {
                Toast.makeText(context, "과거의 시간대를 선택할 수 없습니다.", Toast.LENGTH_SHORT).show()
                timeState = LocalTime.now()
                dateState = LocalDate.now()
            }
        },
        timeState.hour,
        timeState.minute,
        false
    )
    val datePicker = DatePickerDialog(
        context, { _, year, monthOfYear, dayOfMonth ->
            dateState = LocalDate.of(year, monthOfYear, dayOfMonth)
            timePicker.show()
        },
        dateState.year,
        dateState.monthValue,
        dateState.dayOfMonth
    )

    Column(
        modifier = modifier
            .clickable {
                datePicker.show()
            }
    ) {
        Text(text = titleText)
        Text(text = LocalDateTime.of(dateState, timeState).format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm")))
    }
    return LocalDateTime.of(dateState, timeState)
}

@Preview
@Composable
fun DateTimePickerPreview() {
    DateTimePicker(
        titleText = "공연 시작 시간"
    )
}