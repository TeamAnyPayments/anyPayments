package com.artist.wea.components.uidtclass

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem (
    val title: String,
    val icon: ImageVector? = null,
    val screen: @Composable () -> Unit
)