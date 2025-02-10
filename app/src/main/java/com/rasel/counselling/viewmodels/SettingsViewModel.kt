package com.rasel.counselling.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(context: Context) : ViewModel() {

    private val sharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    // Mutable state for each setting
    private var _nightModeEnabled =
        MutableStateFlow(sharedPreferences.getBoolean("night_mode_enabled", false))
    private var _soundEnabled =
        MutableStateFlow(sharedPreferences.getBoolean("sound_enabled", true))
    private var _vibrationEnabled =
        MutableStateFlow(sharedPreferences.getBoolean("vibration_enabled", false))


    val nightModeEnabled : StateFlow<Boolean>  get() = _nightModeEnabled

    val soundEnabled : StateFlow<Boolean>  get() = _soundEnabled
    val vibrationEnabled : StateFlow<Boolean>  get() = _vibrationEnabled

    // Function to update settings status
    fun updateSettings(nightMode: Boolean, sound: Boolean, vibration: Boolean) {
        sharedPreferences.edit().apply {
            putBoolean("night_mode_enabled", nightMode)
            putBoolean("sound_enabled", sound)
            putBoolean("vibration_enabled", vibration)
            apply()
        }
        _nightModeEnabled.value = nightMode
        _soundEnabled.value = sound
        _vibrationEnabled.value = vibration
    }
}
