package com.gemini.assistant.presentation.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gemini.assistant.domain.model.ChatSearchModel
import com.gemini.assistant.domain.usecases.AppUseCases
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiChatSearchState
import com.gemini.assistant.utils.helpers.toHotFlow
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeminiChatSearchViewModel @Inject constructor(private val appUseCases: AppUseCases): ViewModel() {

    val connectivityStatus = appUseCases.connectionHandlerUseCase().toHotFlow(coroutineScope = viewModelScope, initialValue = ConnectivityStatus.Unavailable)

    val chatSearchHistory = appUseCases.retrieveFiveLastChatSearchQueriesUseCase().toHotFlow(coroutineScope = viewModelScope, initialValue = emptyList())

    var geminiChatSearchState by mutableStateOf(GeminiChatSearchState())
        private set

    var isShowScrollDownButton by derivedStateOf {
        mutableStateOf(false)
    }.value
        private set

    private var geminiTypingResponseJob: Job? = null

    fun onEvent(geminiSearchEvent: GeminiSearchEvent) {
        when(geminiSearchEvent) {
            is GeminiSearchEvent.OnSearchInputUpdate -> {
                onSearchInputUpdate(geminiSearchEvent.searchInput)
            }
            is GeminiSearchEvent.IsShowScrollDownButtonUpdate -> {
                isShowScrollDownButtonUpdate(geminiSearchEvent.isShowScrollDownButton)
            }
            is GeminiSearchEvent.OnUserIconBitmapUpdate -> {
                onUserIconBitmapUpdate(geminiSearchEvent.newUserIconBitmap)
            }
            is GeminiSearchEvent.OnPermissionResult -> {
                onPermissionResult(geminiSearchEvent.permission, geminiSearchEvent.isGranted)
            }
            GeminiSearchEvent.OnSearchRequest -> {
                onSearchRequest()
            }
            GeminiSearchEvent.OnDeleteChatSearchOlder -> {
                onDeleteChatSearchOlder()
            }
            GeminiSearchEvent.OnDismissPermissionDialog -> {
                onDismissPermissionDialog()
            }
        }
    }

    private fun onSearchInputUpdate(searchInput: String = "") {
        val updatedSearchInputState = geminiChatSearchState.chatSearchInputState.copy(textFieldSearchInput = searchInput)
        geminiChatSearchState = geminiChatSearchState.copy(chatSearchInputState = updatedSearchInputState)
    }

    private suspend fun insertChatSearchInput(search: String) {
        val chatSearchModel = ChatSearchModel(search = search)
        appUseCases.insertChatSearchUseCase(chatSearchModel = chatSearchModel)
    }

    private fun isShowScrollDownButtonUpdate(isShowScrollDownButton: Boolean) {
        this.isShowScrollDownButton = isShowScrollDownButton
    }

    private fun onUserIconBitmapUpdate(newUserIconBitmap: Bitmap) {
        geminiChatSearchState = geminiChatSearchState.copy(userIconBitmap = newUserIconBitmap)
    }

    private fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (!isGranted && !geminiChatSearchState.visiblePermissionDialogQueue.contains(permission)) {
            val updatedVisiblePermissionDialogQueue = geminiChatSearchState.visiblePermissionDialogQueue.toMutableList()
            updatedVisiblePermissionDialogQueue.add(permission)
            geminiChatSearchState = geminiChatSearchState.copy(visiblePermissionDialogQueue = updatedVisiblePermissionDialogQueue.toList())
        }
    }

    private fun updateChatHistoryResponseValue() {
        val chatHistoryResponse = appUseCases.chatHistoryResponseUseCase()
        geminiChatSearchState = geminiChatSearchState.copy(chatHistoryResponse = chatHistoryResponse)
    }

    private fun updateIsAlreadyStartConversation() {
        geminiChatSearchState = geminiChatSearchState.copy(isAlreadyStartConversation = true)
    }

    private fun updateIsGeminiTypingValue() {
        geminiChatSearchState = geminiChatSearchState.copy(isGeminiTyping = !geminiChatSearchState.isGeminiTyping)
    }

    private fun updateTypingResponse(response: String) {
        val currentTypingResponse = geminiChatSearchState.typingResponse.plus(response)
        geminiChatSearchState = geminiChatSearchState.copy(typingResponse = currentTypingResponse)
    }
    private fun updateTextSearchInput() {
        val textFieldSearchInput = geminiChatSearchState.chatSearchInputState.textFieldSearchInput
        val updatedSearchInputState = geminiChatSearchState.chatSearchInputState.copy(textSearchInput = textFieldSearchInput)
        geminiChatSearchState = geminiChatSearchState.copy(chatSearchInputState = updatedSearchInputState)
    }

    private fun clearTypingResponse() {
        geminiChatSearchState = geminiChatSearchState.copy(typingResponse = "")
    }

    private fun clearTextFieldSearchInput() {
        val updatedSearchInputState = geminiChatSearchState.chatSearchInputState.copy(textFieldSearchInput = "")
        geminiChatSearchState = geminiChatSearchState.copy(chatSearchInputState = updatedSearchInputState)
    }

    private fun onDeleteChatSearchOlder() {
        viewModelScope.launch {
            appUseCases.deleteChatSearchOlderUseCase()
        }
    }

    private fun onDismissPermissionDialog() {
        val updatedVisiblePermissionDialogQueue = geminiChatSearchState.visiblePermissionDialogQueue.toMutableList()
        updatedVisiblePermissionDialogQueue.removeFirst()
        geminiChatSearchState = geminiChatSearchState.copy(visiblePermissionDialogQueue = updatedVisiblePermissionDialogQueue.toList())
    }

    private fun onSearchRequest() {
        if (!geminiChatSearchState.isAlreadyStartConversation) updateIsAlreadyStartConversation()

        val textFieldSearchText = geminiChatSearchState.chatSearchInputState.textFieldSearchInput
        geminiTypingResponseJob?.cancel()
        geminiTypingResponseJob = appUseCases.chatSearchResponseUseCase(searchText = textFieldSearchText)
            .onStart {
                insertChatSearchInput(textFieldSearchText)
                updateIsGeminiTypingValue()
                updateTextSearchInput()
                clearTextFieldSearchInput()
            }
            .onEach { geminiSearchResponse ->
                updateTypingResponse(geminiSearchResponse)
            }
            .onCompletion {
                updateTextSearchInput()
                clearTypingResponse()
                updateIsGeminiTypingValue()
                updateChatHistoryResponseValue()
            }
            .launchIn(viewModelScope)
    }
}