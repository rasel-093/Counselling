package com.rasel.counselling.screens

import com.rasel.counselling.R
import com.rasel.counselling.navigation.Screen

val gridItems = listOf(
    ScreenItem(Screen.BreathingScreen.route, title = "Breathing", R.drawable.breathing),
    ScreenItem(Screen.ListeningScreen.route, title = "Listening", R.drawable.listening),
    ScreenItem(Screen.QuotesScreen.route, title = "Quotes", R.drawable.quote)
)