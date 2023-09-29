package com.artist.wea.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.artist.wea.R
import com.artist.wea.constants.get12TextStyle
import com.artist.wea.constants.get14TextStyle
import com.artist.wea.constants.getDefTextStyle
import com.artist.wea.constants.getToggleButtonColors

// 환경 설정 아이템
@Composable
fun SettingItem(
    optionName:String = stringResource(id = R.string.empty_text), // 설정 명
    description:String = stringResource(id = R.string.empty_text), // 설명
    modifier: Modifier = Modifier,
    versionText:String = stringResource(id = R.string.empty_text), // 버전 정보
):Boolean{
    var checked = remember { mutableStateOf(true) } // toggle btn flag
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight().padding(0.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(4f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = optionName,
                style = getDefTextStyle()
            )
            Text(
                text = description,
                style = get12TextStyle()
                    .copy(
                        color = colorResource(id = R.color.mono500)
                    )
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        // 버튼
        if(versionText.isEmpty()){
            Switch(
                modifier = Modifier
                    .semantics { contentDescription = "Demo" }
                    .weight(1f),
                checked = checked.value,
                onCheckedChange = { checked.value = it },
                colors = getToggleButtonColors()
            )
        }else {
            Text(
                text = versionText,
                style = get14TextStyle()
                    .copy(
                        color = colorResource(id = R.color.mono700),
                        textAlign = TextAlign.Center
                    ),
                modifier = Modifier.weight(1f)
            )
        }
    }
    return if(versionText.isEmpty()) checked.value else false
}