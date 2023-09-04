package com.artist.wea.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.artist.wea.R
import com.artist.wea.constants.getButtonColor
import com.artist.wea.constants.getDefTextStyle

@Composable
fun VerifyInputForm(
    verifyText:String = stringResource(id = R.string.empty_text),
    hintText:String = stringResource(id = R.string.text_input_guide),
    btnText:String = stringResource(id = R.string.text_value_verify),
    buttonActions:() -> Unit = {},
    isDisable:Boolean = false,
    second:Int = 300
):String{

    var inputText = remember { mutableStateOf("") }
    val context = LocalContext.current; // context

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Row(modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp, 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
            val timerEndText = stringResource(R.string.text_end_of_timer)
            Text(
                text = verifyText,
                style = if(!isDisable) getDefTextStyle()
                else getDefTextStyle()
                    .copy(color = colorResource(id = R.color.mono300))
            )
            Spacer(modifier = Modifier.width(8.dp))
            // inputForm 으로부터 값을 받고 리턴
            inputText.value = InputForm(
                hintText = hintText,
                modifier = Modifier
                    .width(178.dp)
                    .wrapContentHeight(),
                isDisable = isDisable,
                isPassword = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                enabled = isDisable,
                onClick = {
                  if (second > 0){
                      buttonActions()
                  }else {
                      Toast.makeText(context,
                          timerEndText, Toast.LENGTH_SHORT).show()
                  }
                },
                colors = getButtonColor(),
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(0.dp),
            ){
                Text(
                    text = btnText,
                    style = getDefTextStyle().copy(
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.white)
                    )
                )
            }
        }
    }

    return inputText.value
}