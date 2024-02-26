package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatSearchHistoryItem(
    searchHistoryItem: String,
    isLastChatSearchHistoryItem: Boolean,
    onItemClick: (String) -> Unit
) {

    val scrollState = rememberScrollState()

    var searchHistoryItemWidth by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.horizontalScroll(scrollState)
    ) {
        ChatSearchHistoryItemText(
            searchHistoryItem = searchHistoryItem,
            onItemClick = onItemClick,
            onSearchHistoryItemWidth = { itemWidth ->
                searchHistoryItemWidth = itemWidth
            }
        )
        if (!isLastChatSearchHistoryItem) {
            ChatSearchHistoryItemDivider(searchHistoryItemWidth = searchHistoryItemWidth.dp)
        }
    }
}