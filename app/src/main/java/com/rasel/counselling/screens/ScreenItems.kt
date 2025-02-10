package com.rasel.counselling.screens

import com.rasel.counselling.R
import com.rasel.counselling.navigation.Screen

val gridItems = listOf(
    ScreenItem(Screen.BreathingScreen.route, title = "Breathing", R.drawable.breathing),
    ScreenItem(Screen.ListeningScreen.route, title = "Listening", R.drawable.listen),
    ScreenItem(Screen.QuotesScreen.route, title = "Quotes", R.drawable.quote),
    ScreenItem(Screen.QuranScreen.route, title = "Quran", R.drawable.quran),
    ScreenItem(Screen.HadithScreen.route, title = "Hadith", R.drawable.hadith),
    ScreenItem(Screen.StoryScreen.route, title = "Story", R.drawable.story)
)