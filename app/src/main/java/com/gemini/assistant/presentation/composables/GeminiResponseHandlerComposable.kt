package com.gemini.assistant.presentation.composables

import androidx.compose.runtime.Composable
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.utils.GeminiResult

@Composable
fun GeminiResponseHandlerComposable(geminiResponse: GeminiResult<SearchResponseModel>) {
    when(geminiResponse) {
        is GeminiResult.Error -> TODO()
        is GeminiResult.Loading -> TODO()
        is GeminiResult.Success -> TODO()
        is GeminiResult.Undefined -> TODO()
    }
}