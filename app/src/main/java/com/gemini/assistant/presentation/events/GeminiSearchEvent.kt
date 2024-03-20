package com.gemini.assistant.presentation.events

import android.graphics.Bitmap

sealed class GeminiSearchEvent {

    data class OnSearchRequestTextUpdate(val searchRequestText: String): GeminiSearchEvent()
    data class OnSearchRequestImagesUpdate(val searchRequestImages: List<Bitmap>): GeminiSearchEvent()
    data class IsShowScrollDownButtonUpdate(val isShowScrollDownButton: Boolean): GeminiSearchEvent()
    data class OnPermissionResult(val permission: String, val isGranted: Boolean): GeminiSearchEvent()
    data class OnSearchRequestImageDelete(val image: Bitmap): GeminiSearchEvent()
    data class OnSearchRequestImageExpand(val image: Bitmap): GeminiSearchEvent()
    data object OnSearchRequestImageContract: GeminiSearchEvent()
    data object OnDismissPermissionDialog: GeminiSearchEvent()
    data object OnSearchRequest: GeminiSearchEvent()
}