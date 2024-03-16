package com.gemini.assistant.utils.helpers

sealed class ScreenRoutes(val route: String) {

    data object Graph: ScreenRoutes("Graph")
    data object GeminiChat: ScreenRoutes("Gemini Chat")
    data object GeminiMessage: ScreenRoutes("Gemini Message")
}