package com.rasel.counselling.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rasel.counselling.screens.Breathing
import com.rasel.counselling.screens.Home
import com.rasel.counselling.screens.Listening
import com.rasel.counselling.screens.Quotes
import com.rasel.counselling.screens.SettingsScreen

@Composable
fun NavHostContainer(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {
        composable(Screen.BreathingScreen.route) {
            Breathing(navHostController)
        }
        composable(Screen.SettingScreen.route) {
            SettingsScreen(navHostController)
        }
        composable(Screen.HomeScreen.route) {
            Home(navHostController)
        }
        composable(Screen.ListeningScreen.route) {
            Listening(navHostController)
        }
        composable(Screen.QuotesScreen.route) {
            Quotes(navHostController = navHostController)
        }
    }
}