package com.rasel.counselling.musicplayer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.rasel.counselling.R

class AudioService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private var currentAudioResId: Int? = null
    private var isForeground = false

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val audioResId = intent?.getIntExtra("AUDIO_RES_ID", 0) ?: 0

        if (audioResId != 0) {
            playAudio(audioResId)
        }

        return START_STICKY
    }

    private fun playAudio(audioResId: Int) {
        if (currentAudioResId == audioResId && mediaPlayer?.isPlaying == true) {
            // If the same audio is playing, just pause it
            mediaPlayer?.pause()
            stopForeground(true)
            isForeground = false
        } else {
            mediaPlayer?.release() // Release any existing media player
            mediaPlayer = MediaPlayer.create(this, audioResId).apply {
                setOnCompletionListener {
                    stopSelf() // Stop service when playback completes
                }
                start()
            }

            // Ensure the service runs in the foreground
            showNotification("Playing audio")
            currentAudioResId = audioResId
        }
    }

    private fun showNotification(content: String) {
        val channelId = "audio_playback_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Audio Playback",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Counseling App")
            .setContentText(content)
            .setSmallIcon(R.drawable.play_circle)
            .setOngoing(true)
            .build()

        startForeground(1, notification) // This prevents crashes
        isForeground = true
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}

