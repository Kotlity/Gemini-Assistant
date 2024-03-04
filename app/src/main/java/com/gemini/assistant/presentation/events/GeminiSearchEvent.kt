package com.gemini.assistant.presentation.events

import android.graphics.Bitmap

sealed class GeminiSearchEvent {

    data class OnSearchInputUpdate(val searchInput: String): GeminiSearchEvent()
    data class IsShowScrollDownButtonUpdate(val isShowScrollDownButton: Boolean): GeminiSearchEvent()
    data class OnPermissionResult(val permission: String, val isGranted: Boolean): GeminiSearchEvent()
    data class OnInsertUserPhoto(val userPhotoPath: String): GeminiSearchEvent()
    data object OnSearchRequest: GeminiSearchEvent()
    data object OnDeleteChatSearchOlder: GeminiSearchEvent()
    data object OnDismissPermissionDialog: GeminiSearchEvent()
}