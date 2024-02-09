package com.gemini.assistant.presentation.states

data class GeminiSearchState(
    val searchInput: String = "",
    val searchImages: List<String> = emptyList(),
    val searchResponse: String = "",
    val isGeminiTyping: Boolean = false,
    val isAlreadyStartConversation: Boolean = false
)