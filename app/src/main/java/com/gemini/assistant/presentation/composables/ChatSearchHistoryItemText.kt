package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import com.gemini.assistant.utils.Constants

@Composable
fun ChatSearchHistoryItemText(
    searchHistoryItem: String,
    onItemClick: (String) -> Unit
) {

    val scrollState = rememberScrollState()

    ClickableText(
        modifier = Modifier.horizontalScroll(scrollState),
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