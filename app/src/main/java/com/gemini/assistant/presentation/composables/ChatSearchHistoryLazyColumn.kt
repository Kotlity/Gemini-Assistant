package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R
import com.gemini.assistant.domain.model.ChatSearchModel

@Composable
fun ChatSearchHistoryLazyColumn(
    modifier: Modifier = Modifier,
    chatSearchHistory: List<ChatSearchModel>,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(id = R.dimen._2dp)),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(dimensionResource(id = R.dimen._2dp), Alignment.CenterVertically),
    onItemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
    ) {
        itemsIndexed(chatSearchHistory) { index, searchHistoryItem ->
            ChatSearchHistoryItem(
                searchHistoryItem = searchHistoryItem.search,
                isLastChatSearchHistoryItem = index == chatSearchHistory.size - 1,
                onItemClick = onItemClick
            )
        }
    }
}