package com.gemini.assistant.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gemini.assistant.domain.usecases.AppUseCases
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiSearchState
import com.gemini.assistant.utils.helpers.toHotFlow
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class GeminiSearchViewModel @Inject constructor(private val appUseCases: AppUseCases): ViewModel() {

    val connectivityStatus = appUseCases.connectionHandlerUseCase().toHotFlow(coroutineScope = viewModelScope, initialValue = ConnectivityStatus.Unavailable)

    var geminiSearchState by mutableStateOf(GeminiSearchState())
        private set

    private var geminiTypingResponseJob: Job? = null

    fun onEvent(geminiSearchEvent: GeminiSearchEvent) {
        when(geminiSearchEvent) {
            is GeminiSearchEvent.OnSearchInputUpdate -> {
                onSearchInputUpdate(geminiSearchEvent.searchInput)
            }
            is GeminiSearchEvent.OnSearchImagesUpdate -> {
                onSearchImagesUpdate(geminiSearchEvent.images)
            }
            is GeminiSearchEvent.OnRemoveImageFromSearch -> {
                onRemoveImageFromSearch(geminiSearchEvent.image)
            }
            GeminiSearchEvent.OnSearchRequest -> {
                onSearchRequest()
            }
        }
    }

    private fun onSearchInputUpdate(searchInput: String) {
        val updatedSearchInputState = geminiSearchState.searchInputState.copy(textFieldSearchInput = searchInput)
        geminiSearchState = geminiSearchState.copy(searchInputState = updatedSearchInputState)
    }

    private fun onSearchImagesUpdate(bitmaps: List<String>) {
        geminiSearchState = geminiSearchState.copy(searchImages = bitmaps)
    }

    private fun onRemoveImageFromSearch(image: String) {
        val updatedSearchImages = geminiSearchState.searchImages.toMutableList()
        updatedSearchImages.remove(image)
        geminiSearchState = geminiSearchState.copy(searchImages = updatedSearchImages.toList())
    }

    private fun updateChatHistoryResponseValue() {
        val chatHistoryResponse = appUseCases.chatHistoryResponseUseCase()
        geminiSearchState = geminiSearchState.copy(chatHistoryResponse = chatHistoryResponse)
        Log.e("MyTag", "chat history response state: ${geminiSearchState.chatHistoryResponse}")
    }

    private fun updateIsAlreadyStartConversation() {
        geminiSearchState = geminiSearchState.copy(isAlreadyStartConversation = true)
    }

    private fun updateIsGeminiTypingValue() {
        geminiSearchState = geminiSearchState.copy(isGeminiTyping = !geminiSearchState.isGeminiTyping)
    }

    private fun clearTypingResponse() {
        geminiSearchState = geminiSearchState.copy(typingResponse = "")
    }

    private fun updateTextSearchInput() {
        val textFieldSearchInput = geminiSearchState.searchInputState.textFieldSearchInput
        val updatedSearchInputState = geminiSearchState.searchInputState.copy(textSearchInput = textFieldSearchInput)
        geminiSearchState = geminiSearchState.copy(searchInputState = updatedSearchInputState)
    }

    private fun clearTextFieldSearchInput() {
        val updatedSearchInputState = geminiSearchState.searchInputState.copy(textFieldSearchInput = "")
        geminiSearchState = geminiSearchState.copy(searchInputState = updatedSearchInputState)
    }

    private fun clearTextSearchInput() {
        val updatedSearchInputState = geminiSearchState.searchInputState.copy(textSearchInput = "")
        geminiSearchState = geminiSearchState.copy(searchInputState = updatedSearchInputState)
    }

    private fun clearSearchImages() {
        geminiSearchState = geminiSearchState.copy(searchImages = emptyList())
    }

    private fun onSearchRequest() {
        if (!geminiSearchState.isAlreadyStartConversation) updateIsAlreadyStartConversation()

        val textFieldSearchText = geminiSearchState.searchInputState.textFieldSearchInput
        val searchImages = geminiSearchState.searchImages
        geminiTypingResponseJob?.cancel()
        geminiTypingResponseJob = appUseCases.searchResponseUseCase(searchText = textFieldSearchText, searchImages = searchImages)
            .onStart {
                updateIsGeminiTypingValue()
                updateTextSearchInput()
                clearTextFieldSearchInput()
                clearSearchImages()
            }
            .onEach { geminiSearchResponse ->
                val concatenatedResponse = geminiSearchState.typingResponse.plus(geminiSearchResponse)
                geminiSearchState = geminiSearchState.copy(typingResponse = concatenatedResponse)
            }
            .onCompletion {
                clearTextSearchInput()
                clearTypingResponse()
                updateIsGeminiTypingValue()
                updateChatHistoryResponseValue()
            }
            .launchIn(viewModelScope)
    }
}