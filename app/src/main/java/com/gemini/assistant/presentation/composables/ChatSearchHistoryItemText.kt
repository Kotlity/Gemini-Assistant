package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.AnnotatedString
import com.gemini.assistant.utils.Constants

@Composable
fun ChatSearchHistoryItemText(
    searchHistoryItem: String,
    onItemClick: (String) -> Unit,
    onSearchHistoryItemWidth: (Int) -> Unit
) {

    ClickableText(
        modifier = Modifier
            .onGloballyPositioned { layoutCoordinates ->
                val width = layoutCoordinates.size.width
                onSearchHistoryItemWidth(width)
            },
        text = AnnotatedString(searchHistoryItem),
        style = MaterialTheme.typography.bodyMedium.copy(
            fontSize = Constants._18sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        onClick = {
            onItemClick(searchHistoryItem)
        },
        maxLines = 1
    )
}