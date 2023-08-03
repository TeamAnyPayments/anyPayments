package com.artist.wea.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.artist.wea.components.InputForm
import com.artist.wea.components.TitleInputForm

@Composable
fun UserRegisterPage(modifier: Modifier = Modifier){

    var nameInputText = remember { mutableStateOf("") }
    nameInputText.value = TitleInputForm("이름");


}