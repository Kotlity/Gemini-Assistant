package com.gemini.assistant.presentation.events

sealed class GeminiChatSearchEvent {

    data class OnChatSearchInputUpdate(val searchInput: String): GeminiChatSearchEvent()
    data class IsShowScrollDownButtonUpdate(val isShowScrollDownButton: Boolean): GeminiChatSearchEvent()
    data class OnPermissionResult(val permission: String, val isGranted: Boolean): GeminiChatSearchEvent()
    data class OnInsertUserPhoto(val userPhotoPath: String): GeminiChatSearchEvent()
    data object OnChatSearchRequest: GeminiChatSearchEvent()
    data object OnDeleteChatChatSearchOlder: GeminiChatSearchEvent()
    data object OnDismissPermissionDialog: GeminiChatSearchEvent()
}