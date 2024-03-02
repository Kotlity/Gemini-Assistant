package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R

@Composable
fun ChatResponseLazyColumn(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(dimensionResource(id = R.dimen._5dp)),
    lazyListState: LazyListState,
    customTextModifier: Modifier = Modifier,
    textSearchInput: String = "",
    chatHistoryResponse: List<String> = emptyList(),
    typingResponse: String = "",
    isGeminiTyping: Boolean = false,
    bitmap: ImageBitmap? = null,
    onIconClick: (() -> Unit)? = null,
    onTextCopied: (String) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        state = lazyListState
    ) {
        items(chatHistoryResponse.size) { index ->
            ChatSection(
                textModifier = customTextModifier,
                response = chatHistoryResponse,
                index = index,
                bitmap = bitmap,
                onIconClick = if (!isGeminiTyping) onIconClick else null,
                onTextCopied = onTextCopied
            )
        }
        if (textSearchInput.isNotBlank()) {
            item {
                ChatUserSection(userInput = textSearchInput)
            }
        }
        if (typingResponse.isNotBlank()) {
            item {
                ChatTextResponse(
                    modifier = customTextModifier,
                    text = typingResponse
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