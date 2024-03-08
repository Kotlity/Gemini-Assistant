package com.gemini.assistant.utils.helpers

sealed class ScreenRoutes(val route: String) {

    data object GeminiChat: ScreenRoutes("Gemini Chat")
    data object GeminiMessage: ScreenRoutes("Gemini Message")
}