package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun ChatSearchHistoryItem(
    searchHistoryItem: String,
    isLastChatSearchHistoryItem: Boolean,
    onItemClick: (String) -> Unit
) {

    Column {
        ChatSearchHistoryItemText(
            searchHistoryItem = searchHistoryItem,
            onItemClick = onItemClick
        )
        if (!isLastChatSearchHistoryItem) ChatSearchHistoryItemDivider()
    }
}