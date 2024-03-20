package com.gemini.assistant.presentation.composables

import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingSection(
    modifier: Modifier = Modifier
) {
    ContentBox(modifier = modifier) {
        LinearProgressIndicator()
    }
}