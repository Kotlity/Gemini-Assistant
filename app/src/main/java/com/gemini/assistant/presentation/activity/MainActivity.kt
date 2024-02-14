package com.gemini.assistant.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.gemini.assistant.presentation.screens.GeminiSearchScreen
import com.gemini.assistant.presentation.theme.GeminiHelperTheme
import com.gemini.assistant.presentation.viewmodels.GeminiSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiHelperTheme {
                val geminiSearchViewModel = hiltViewModel<GeminiSearchViewModel>()

                val geminiSearchState = geminiSearchViewModel.geminiSearchState
                val geminiConnectivityStatus = geminiSearchViewModel.connectivityStatus

                GeminiSearchScreen(
                    geminiSearchState = geminiSearchState,
                    connectivityStatus = geminiConnectivityStatus,
                    onGeminiSearchEvent = geminiSearchViewModel::onEvent)
            }
        }
    }
}