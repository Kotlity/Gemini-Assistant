package com.gemini.assistant.utils.helpers

import androidx.navigation.NavHostController

interface Navigator {

    fun navigate(route: String)
}

class AppComposeNavigator(private val navHostController: NavHostController): Navigator {

    override fun navigate(route: String) {
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(route)
        }
    }
}