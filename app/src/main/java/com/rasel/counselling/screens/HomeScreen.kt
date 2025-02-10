package com.rasel.counselling.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rasel.counselling.R
import com.rasel.counselling.components.GridItemView
import com.rasel.counselling.navigation.Screen
import com.rasel.counselling.objects.LocalSetting
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navHostController: NavHostController) {
    val context = LocalContext.current
    //val settingsViewModel = SettingsViewModel(context)
    //val nightenable = settingsViewModel.nightModeEnabled.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Counselling", color = LocalSetting.textColor()) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF01678b)
                ),
                actions = {
                    IconButton(
                        onClick = { navHostController.navigate(Screen.SettingScreen.route) }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.setting_icon),
                            contentDescription = null,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                    }
                }
            )
        },
        containerColor = LocalSetting.containerColor()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val row = ceil((gridItems.size.toDouble() / 3)).toInt()
            var end: Int = 0

            for (j in 1..row) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    while (end != gridItems.size) {
                        GridItemView(
                            gridItem = gridItems[end],
                            navHostController = navHostController
                        )
                        Log.d("Grid", "row = $j, item = $end")
                        end++
                        if (end % 3 == 0)
                            break
                    }
                }
            }

        }
    }
}