package com.gemini.assistant.presentation.states

import android.graphics.Bitmap

data class GeminiChatSearchState(
    val chatSearchInputState: ChatSearchInputState = ChatSearchInputState(),
    val chatHistoryResponse: List<String> = emptyList(),
    val visiblePermissionDialogQueue: List<String> = emptyList(),
    val userIconBitmap: Bitmap? = null,
    val typingResponse: String = "",
    val isGeminiTyping: Boolean = false,
    val isAlreadyStartConversation: Boolean = false
)