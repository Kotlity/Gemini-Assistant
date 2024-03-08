package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gemini.assistant.presentation.screens.GeminiChatSearchScreen
import com.gemini.assistant.presentation.viewmodels.GeminiChatSearchViewModel
import com.gemini.assistant.utils.helpers.ScreenRoutes

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = ScreenRoutes.GeminiChat.route
) {

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(ScreenRoutes.GeminiChat.route) {
            val geminiChatSearchViewModel = hiltViewModel<GeminiChatSearchViewModel>()

            val geminiChatSearchState = geminiChatSearchViewModel.geminiChatSearchState
            val connectivityStatus by geminiChatSearchViewModel.connectivityStatus.collectAsStateWithLifecycle()
            val chatSearchHistory by geminiChatSearchViewModel.chatSearchHistory.collectAsStateWithLifecycle()
            val userPhotoPathFromDB by geminiChatSearchViewModel.userPhotoPath.collectAsStateWithLifecycle()
            val isShowScrollDownButton = geminiChatSearchViewModel.isShowScrollDownButton

            GeminiChatSearchScreen(
                geminiChatSearchState = geminiChatSearchState,
                connectivityStatus = connectivityStatus,
                chatSearchHistory = chatSearchHistory,
                userPhotoPathFromDB = userPhotoPathFromDB,
                isShowScrollDownButton = isShowScrollDownButton,
                onGeminiSearchEvent = geminiChatSearchViewModel::onEvent
            )
        }
        composable(ScreenRoutes.GeminiMessage.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = ScreenRoutes.GeminiMessage.route)
            }
        }
    }
}