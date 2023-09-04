package com.artist.wea.util

import android.content.Context
import android.widget.Toast

class ToastManager {
    companion object{
        fun shortToast(
            context: Context,
            text:String,
        ){
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
        fun longToast(
            context: Context,
            text:String,
        ){
            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
        }

    }
}