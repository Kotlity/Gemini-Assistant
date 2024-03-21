package com.gemini.assistant.presentation.events

sealed interface SearchRequestModelCleaner {
    data object OnClear: SearchRequestModelCleaner
}