package com.gemini.assistant.presentation.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.gemini.assistant.utils.helpers.AppComposeNavigator

@Composable
fun AppScreenContent() {

    val navHostController = rememberNavController()

    GeneralContentSurface(
        navHostController = navHostController,
        navigator = AppComposeNavigator(navHostController = navHostController)
    ) { modifier ->
        AppNavigationHost(
            modifier = modifier,
            navHostController = navHostController
        )
    }
}