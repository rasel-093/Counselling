package com.rasel.counselling.hwfunctions
import android.content.Context
import android.media.MediaPlayer
import com.rasel.counselling.R

fun playSound(context: Context) {
    val mediaPlayer = MediaPlayer.create(context, R.raw.beep )
    mediaPlayer.setOnCompletionListener { it.release() }
    mediaPlayer.start()
}
