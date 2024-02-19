package com.rasel.counselling.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.navOptions

fun NavHostController.navigateTo(route: String) {
    navigate(route = route, navOptions = navOptions {
        //Limit backStack
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
//        restoreState = true
    })
}