package com.gemini.assistant.utils.shared_view_model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

@Composable
inline fun <reified VM: ViewModel> NavBackStackEntry.sharedViewModel(navHostController: NavHostController): VM {
    val graphRoute = destination.parent?.route ?: return hiltViewModel()
    val graphNavBackStackEntry = remember(this) {
        navHostController.getBackStackEntry(graphRoute)
    }
    return hiltViewModel(viewModelStoreOwner = graphNavBackStackEntry)
}