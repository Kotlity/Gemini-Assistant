package com.gemini.assistant.presentation.events

sealed class GeminiSearchEvent {

    data class OnSearchInputUpdate(val searchInput: String): GeminiSearchEvent()
    data class IsShowScrollDownButtonUpdate(val isShowScrollDownButton: Boolean): GeminiSearchEvent()
    data object OnSearchRequest: GeminiSearchEvent()
    data object OnDeleteChatSearchOlder: GeminiSearchEvent()
}