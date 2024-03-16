package com.gemini.assistant.presentation.events

import android.graphics.Bitmap

sealed class GeminiSearchEvent {

    data class OnSearchRequestTextUpdate(val searchRequestText: String): GeminiSearchEvent()
    data class OnSearchRequestImagesUpdate(val searchRequestImages: List<Bitmap>): GeminiSearchEvent()
//    data class OnSearchResponseUpdate(val searchResponseModel: SearchResponseModel): GeminiSearchEvent()
//    data class OnSearchResponseTextUpdate(val searchResponse: String): GeminiSearchEvent()
//    data class OnSearchResponseImagesUpdate(val searchResponseImages: List<Bitmap>): GeminiSearchEvent()
    data class IsShowScrollDownButtonUpdate(val isShowScrollDownButton: Boolean): GeminiSearchEvent()
    data class OnPermissionResult(val permission: String, val isGranted: Boolean): GeminiSearchEvent()
    data class OnSearchRequestImageDelete(val image: Bitmap): GeminiSearchEvent()
    data object OnDismissPermissionDialog: GeminiSearchEvent()
    data object OnSearchRequest: GeminiSearchEvent()
}