package com.gemini.assistant.presentation.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gemini.assistant.data.mappers.updateGeminiResult
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.domain.usecases.AppUseCases
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiSearchState
import com.gemini.assistant.utils.GeminiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GeminiSearchViewModel @Inject constructor(private val appUseCases: AppUseCases): ViewModel() {

    var geminiSearchState by mutableStateOf(GeminiSearchState())
        private set

    var isShowScrollDownButton by derivedStateOf {
        mutableStateOf(false)
    }.value
        private set

    private var geminiSearchResponseJob: Job? = null

    fun onEvent(geminiSearchEvent: GeminiSearchEvent) {
        when(geminiSearchEvent) {
            is GeminiSearchEvent.OnSearchRequestTextUpdate -> onSearchRequestTextUpdate(geminiSearchEvent.searchRequestText)
            is GeminiSearchEvent.OnSearchRequestImagesUpdate -> onSearchRequestImagesUpdate(geminiSearchEvent.searchRequestImages)
            is GeminiSearchEvent.IsShowScrollDownButtonUpdate -> isShowScrollDownButtonUpdate(geminiSearchEvent.isShowScrollDownButton)
            is GeminiSearchEvent.OnPermissionResult -> onPermissionResult(geminiSearchEvent.permission, geminiSearchEvent.isGranted)
            is GeminiSearchEvent.OnSearchRequestImageDelete -> onSearchRequestImageDelete(geminiSearchEvent.image)
            GeminiSearchEvent.OnDismissPermissionDialog -> onDismissPermissionDialog()
            GeminiSearchEvent.OnSearchRequest -> onSearchRequest()
        }
    }

    private fun onSearchRequestTextUpdate(searchRequestText: String) {
        val updatedSearchRequestModel = geminiSearchState.searchRequestModel.copy(searchRequest = searchRequestText)
        geminiSearchState = geminiSearchState.copy(searchRequestModel = updatedSearchRequestModel)
    }

    private fun onSearchRequestImagesUpdate(searchRequestImages: List<Bitmap>) {
        val updatedSearchRequestModel = geminiSearchState.searchRequestModel.copy(searchRequestImages = searchRequestImages)
        geminiSearchState = geminiSearchState.copy(searchRequestModel = updatedSearchRequestModel)
    }

    private fun isShowScrollDownButtonUpdate(isShowScrollDownButton: Boolean) {
        this.isShowScrollDownButton = isShowScrollDownButton
    }

    private fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (!isGranted && !geminiSearchState.visiblePermissionDialogQueue.contains(permission)) {
            val updatedVisiblePermissionDialogQueue = geminiSearchState.visiblePermissionDialogQueue.toMutableList()
            updatedVisiblePermissionDialogQueue.add(permission)
            geminiSearchState = geminiSearchState.copy(visiblePermissionDialogQueue = updatedVisiblePermissionDialogQueue.toList())
        }
    }

    private fun onSearchRequestImageDelete(image: Bitmap) {
        val updatedSearchRequestImages = geminiSearchState.searchRequestModel.searchRequestImages.toMutableList()
        updatedSearchRequestImages.remove(image)
        val updatedSearchRequestModel = geminiSearchState.searchRequestModel.copy(searchRequestImages = updatedSearchRequestImages.toList())
        geminiSearchState = geminiSearchState.copy(searchRequestModel = updatedSearchRequestModel)
    }

    private fun onDismissPermissionDialog() {
        val updatedVisiblePermissionDialogQueue = geminiSearchState.visiblePermissionDialogQueue.toMutableList()
        updatedVisiblePermissionDialogQueue.removeFirst()
        geminiSearchState = geminiSearchState.copy(visiblePermissionDialogQueue = updatedVisiblePermissionDialogQueue.toList())
    }

    private fun updateSearchResponse(result: GeminiResult<List<SearchResponseModel>>) {
        geminiSearchState = geminiSearchState.copy(searchResponseModel = result.updateGeminiResult())
    }

    private fun onSearchRequest() {
        val searchRequestModel = geminiSearchState.searchRequestModel
        geminiSearchResponseJob?.cancel()
        geminiSearchResponseJob = appUseCases.searchResponseUseCase(searchRequestData = searchRequestModel)
            .onEach { result ->
               updateSearchResponse(result)
            }
            .launchIn(viewModelScope)
    }

}