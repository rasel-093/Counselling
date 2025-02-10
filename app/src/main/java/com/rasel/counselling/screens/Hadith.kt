package com.rasel.counselling.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.rasel.counselling.navigation.Screen
import com.rasel.counselling.navigation.navigateTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hadith(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hadith") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF01678b)
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(text = "Hadith Screen")
        }
    }
}