package com.artist.wea.util

import com.artist.wea.R
import java.util.Random

object ColorPicker {

    private val pastelColorPool = arrayOf(
        R.color.red100,
        R.color.dark_orange100,
        R.color.beige100,
        R.color.brown100,
        R.color.sky_blue100,
        R.color.pastel_purple100,
        R.color.pastel_green100,
        R.color.pastel_pink100,
        R.color.pastel_yellow100
    )

    fun getRandomPastelColor(): Int {
        return pastelColorPool[Random().nextInt(pastelColorPool.size)]
    }
}