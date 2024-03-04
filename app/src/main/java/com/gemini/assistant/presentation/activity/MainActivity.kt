package com.gemini.assistant.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gemini.assistant.presentation.screens.GeminiChatSearchScreen
import com.gemini.assistant.presentation.theme.GeminiHelperTheme
import com.gemini.assistant.presentation.viewmodels.GeminiChatSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiHelperTheme {
                val geminiChatSearchViewModel = hiltViewModel<GeminiChatSearchViewModel>()

                val geminiChatSearchState = geminiChatSearchViewModel.geminiChatSearchState
                val geminiConnectivityStatus by geminiChatSearchViewModel.connectivityStatus.collectAsStateWithLifecycle()
                val geminiChatSearchHistory by geminiChatSearchViewModel.chatSearchHistory.collectAsStateWithLifecycle()
                val userPhotoPathFromDB by geminiChatSearchViewModel.userPhotoPath.collectAsStateWithLifecycle()
                val isShowScrollDownButton = geminiChatSearchViewModel.isShowScrollDownButton

                GeminiChatSearchScreen(
                    geminiChatSearchState = geminiChatSearchState,
                    connectivityStatus = geminiConnectivityStatus,
                    chatSearchHistory = geminiChatSearchHistory,
                    userPhotoPathFromDB = userPhotoPathFromDB,
                    isShowScrollDownButton = isShowScrollDownButton,
                    onGeminiSearchEvent = geminiChatSearchViewModel::onEvent)
            }
        }
    }
}