package com.gemini.assistant.presentation.events

sealed class GeminiSearchEvent {

    data class OnSearchInputUpdate(val searchInput: String): GeminiSearchEvent()
    data class OnSearchImagesUpdate(val images: List<String>): GeminiSearchEvent()
    data class OnRemoveImageFromSearch(val image: String): GeminiSearchEvent()
    data object OnSearchRequest: GeminiSearchEvent()
}