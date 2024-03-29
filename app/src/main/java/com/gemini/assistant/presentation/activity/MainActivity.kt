package com.gemini.assistant.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gemini.assistant.presentation.composables.AppScreenContent
import com.gemini.assistant.presentation.theme.GeminiHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiHelperTheme {
                AppScreenContent()
            }
        }
    }
}