package com.gemini.assistant.presentation.states

data class GeminiChatSearchState(
    val chatSearchInputState: ChatSearchInputState = ChatSearchInputState(),
    val chatHistoryResponse: List<String> = emptyList(),
    val typingResponse: String = "",
    val isGeminiTyping: Boolean = false,
    val isAlreadyStartConversation: Boolean = false
)