package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants

@Composable
fun ChatLazyColumn(
    modifier: Modifier = Modifier,
    customTextModifier: Modifier = Modifier,
    chatHistoryResponse: List<String> = emptyList(),
    typingResponse: String = "",
    isGeminiTyping: Boolean = false
) {
    LazyColumn(modifier = modifier) {
        items(chatHistoryResponse) { response ->
            ModelResponseSection(
                modifier = customTextModifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                response = response
            )
        }
        if (typingResponse.isNotBlank()) {
            item {
                CustomText(
                    modifier = customTextModifier,
                    text = typingResponse,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = Constants._16sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
            }
        }
        if (isGeminiTyping) {
            item {
                TypingAnimationWidget(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen._3dp),
                            bottom = dimensionResource(id = R.dimen._5dp)
                        )
                )
            }
        }
    }
}