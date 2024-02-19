package com.rasel.counselling.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rasel.counselling.navigation.Screen
import com.rasel.counselling.navigation.navigateTo
import com.rasel.counselling.objects.LocalSetting
import com.rasel.counselling.viewmodels.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val settingsViewModel = SettingsViewModel(context)
    val nightModeEnabled = settingsViewModel.nightModeEnabled.collectAsState().value
    val soundEnabled = settingsViewModel.soundEnabled.collectAsState().value
    val vibrationEnabled = settingsViewModel.vibrationEnabled.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings",color = LocalSetting.textColor()) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3)
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        //Handle back button option
                        navHostController.navigateTo(Screen.HomeScreen.route)
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        containerColor = LocalSetting.containerColor()
    ) { paddingValues ->

        // Read settings status from ViewModel
        var isNightModeEnabled by rememberSaveable { mutableStateOf(nightModeEnabled) }
        var isSoundEnabled by rememberSaveable { mutableStateOf(soundEnabled) }
        var isVibrationEnabled by rememberSaveable { mutableStateOf(vibrationEnabled) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Night Mode Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Night Mode",
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LocalSetting.textColor()
                )
                Switch(
                    checked = isNightModeEnabled,
                    onCheckedChange = {
                        isNightModeEnabled = it
                        settingsViewModel.updateSettings(
                            isNightModeEnabled,
                            isSoundEnabled,
                            isVibrationEnabled
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Black,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }


            // Sound state switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sound",
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LocalSetting.textColor()
                )
                Switch(
                    checked = isSoundEnabled,
                    onCheckedChange = {
                        isSoundEnabled = it
                        settingsViewModel.updateSettings(
                            isNightModeEnabled,
                            isSoundEnabled,
                            isVibrationEnabled
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Black,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }


            // Vibration Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Vibration",
                    modifier = Modifier.padding(bottom = 4.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = LocalSetting.textColor()
                )
                Switch(
                    checked = isVibrationEnabled,
                    onCheckedChange = {
                        isVibrationEnabled = it
                        settingsViewModel.updateSettings(
                            isNightModeEnabled,
                            isSoundEnabled,
                            isVibrationEnabled
                        )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Black,
                        checkedTrackColor = Color.Gray,
                        uncheckedTrackColor = Color.LightGray
                    )
                )
            }
        }
    }
}
