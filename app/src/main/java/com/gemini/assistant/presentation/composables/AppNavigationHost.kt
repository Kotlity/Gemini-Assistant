package com.gemini.assistant.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.gemini.assistant.presentation.screens.GeminiChatSearchScreen
import com.gemini.assistant.presentation.screens.GeminiSearchScreen
import com.gemini.assistant.presentation.viewmodels.GeminiChatSearchViewModel
import com.gemini.assistant.presentation.viewmodels.GeminiSearchViewModel
import com.gemini.assistant.presentation.viewmodels.InternetConnectionViewModel
import com.gemini.assistant.utils.helpers.ScreenRoutes
import com.gemini.assistant.utils.shared_view_model.sharedViewModel

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    route: String = ScreenRoutes.Graph.route
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = route
    ) {
        navigation(
            startDestination = ScreenRoutes.GeminiChat.route,
            route = route
        ) {
            composable(ScreenRoutes.GeminiChat.route) { navBackStackEntry ->
                val internetConnectionViewModel = navBackStackEntry.sharedViewModel<InternetConnectionViewModel>(navHostController = navHostController)
                val geminiChatSearchViewModel = hiltViewModel<GeminiChatSearchViewModel>()

                val geminiChatSearchState = geminiChatSearchViewModel.geminiChatSearchState
                val connectivityStatus by internetConnectionViewModel.internetConnection.collectAsStateWithLifecycle()
                val chatSearchHistory by geminiChatSearchViewModel.chatSearchHistory.collectAsStateWithLifecycle()
                val userPhotoPathFromDB by geminiChatSearchViewModel.userPhotoPath.collectAsStateWithLifecycle()
                val isShowScrollDownButton = geminiChatSearchViewModel.isShowScrollDownButton

                GeminiChatSearchScreen(
                    geminiChatSearchState = geminiChatSearchState,
                    connectivityStatus = connectivityStatus,
                    chatSearchHistory = chatSearchHistory,
                    userPhotoPathFromDB = userPhotoPathFromDB,
                    isShowScrollDownButton = isShowScrollDownButton,
                    onGeminiChatSearchEvent = geminiChatSearchViewModel::onEvent
                )
            }
            composable(ScreenRoutes.GeminiMessage.route) { navBackStackEntry ->
                val internetConnectionViewModel = navBackStackEntry.sharedViewModel<InternetConnectionViewModel>(navHostController = navHostController)
                val geminiSearchViewModel = hiltViewModel<GeminiSearchViewModel>()

                val geminiSearchState = geminiSearchViewModel.geminiSearchState
                val searchRequestModelCleaner = geminiSearchViewModel.searchRequestModelCleanerFlow
                val isShowScrollDownButton = geminiSearchViewModel.isShowScrollDownButton

                val connectivityStatus by internetConnectionViewModel.internetConnection.collectAsStateWithLifecycle()

                GeminiSearchScreen(
                    geminiSearchState = geminiSearchState,
                    searchRequestModelCleaner = searchRequestModelCleaner,
                    connectivityStatus = connectivityStatus,
                    onGeminiSearchEvent = geminiSearchViewModel::onEvent,
                    isShowScrollDownButton = isShowScrollDownButton
                )
            }
        }
    }
}