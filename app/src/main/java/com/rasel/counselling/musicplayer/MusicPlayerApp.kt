package com.rasel.counselling.musicplayer

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.rasel.counselling.R

@Composable
fun MusicPlayerApp(context: Context) {
    val audioFiles = listOf(
        Pair("Relaxing Sound", R.raw.mindfullness1),
        Pair("Meditation", R.raw.birds_singing_calm_river)
    )

    var playingAudio by remember { mutableStateOf<Int?>(null) }

    LazyColumn {
        items(audioFiles) { (name, resId) ->
            AudioItem(name, resId, playingAudio) { selectedAudio ->
                if (playingAudio == selectedAudio) {
                    context.stopService(Intent(context, AudioService::class.java))
                    playingAudio = null
                } else {
                    val intent = Intent(context, AudioService::class.java).apply {
                        putExtra("AUDIO_RES_ID", selectedAudio)
                    }
                    ContextCompat.startForegroundService(context, intent)
                    playingAudio = selectedAudio
                }
            }
        }
    }
}

@Composable
fun AudioItem(name: String, audioResId: Int, playingAudio: Int?, onClick: (Int) -> Unit) {
    val isPlaying = playingAudio == audioResId
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(audioResId) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name, modifier = Modifier.weight(1f))
        Icon(
            painter = if(isPlaying) painterResource(R.drawable.pause) else painterResource(R.drawable.play),
            contentDescription = "play/pause"
        )
    }
}

