package com.artist.wea.util

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.util.Timer
import java.util.TimerTask

class WeaTimer {
    fun getTimerTask (mTimer: Timer, textView: TextView, sec:Int): TimerTask {
        var mSecond: Int = sec;
        val mTimerTask = object : TimerTask() {
            override fun run() {
                // SoboroConstant.isTimerEnd = false;
                val mHandler = Handler(Looper.getMainLooper())
                mHandler.postDelayed({
                    // 반복실행할 구문
                    mSecond--
//                        Log.d(ContentValues.TAG,"$mSecond || ${mSecond/60}분 ${mSecond%60}초");
                    textView.text = "남은시간 : ${mSecond/60}분 ${mSecond%60}초";
                    if (mSecond <= 0) {
                        mTimer.cancel()
                        // SoboroConstant.isTimerEnd = true;
                    }
                }, 0)
            }
        }
        return mTimerTask
    }
}