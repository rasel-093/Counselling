package com.rasel.counselling.objects

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.rasel.counselling.hwfunctions.playSound
import com.rasel.counselling.hwfunctions.vibrate
import com.rasel.counselling.viewmodels.SettingsViewModel

object LocalSetting {
    //Color selection
    @Composable
    fun textColor():Color{
        val context = LocalContext.current
        val settingsViewModel = SettingsViewModel(context)
        val nightModeEnabled = settingsViewModel.nightModeEnabled.collectAsState().value

       return if (nightModeEnabled) Color(0xFFFFFFFF) else Color(0xFF312F2F)
    }

    @Composable
    fun containerColor(): Color {
        val context = LocalContext.current
        val settingsViewModel = SettingsViewModel(context)
        val nightModeEnabled = settingsViewModel.nightModeEnabled.collectAsState().value
        return if (nightModeEnabled) Color(0xFF222020) else Color(0xFFFFFFFF)
    }


    @Composable
    fun circleColor(): Color{
        val context = LocalContext.current
        val settingsViewModel = SettingsViewModel(context)
        val nightModeEnabled = settingsViewModel.nightModeEnabled.collectAsState().value

        return if (nightModeEnabled) Color.DarkGray  else Color(0xFF48A6C7)
    }
    @Composable
    fun primaryColor(): Color{
        val context = LocalContext.current
        val settingsViewModel = SettingsViewModel(context)
        val nightModeEnabled = settingsViewModel.nightModeEnabled.collectAsState().value
        return if (nightModeEnabled) Color.DarkGray  else Color(0xFF01678b)
    }

    //sound and vibration
    fun vibration(context: Context){
        val settingsViewModel = SettingsViewModel(context)
        val vibrationEnabled = settingsViewModel.vibrationEnabled.value
        if(vibrationEnabled)
            vibrate(context)
    }

    fun sound(context: Context){
        val settingsViewModel = SettingsViewModel(context)
        val soundEnabled = settingsViewModel.soundEnabled.value
        if(soundEnabled)
            playSound(context)
    }
}