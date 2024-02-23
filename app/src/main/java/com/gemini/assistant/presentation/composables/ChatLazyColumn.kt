package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import com.gemini.assistant.R

@Composable
fun ChatLazyColumn(
    modifier: Modifier = Modifier,
    lazyListState: LazyListState,
    customTextModifier: Modifier = Modifier,
    textSearchInput: String = "",
    chatHistoryResponse: List<String> = emptyList(),
    typingResponse: String = "",
    isGeminiTyping: Boolean = false
) {

    val clipboardManager = LocalClipboardManager.current

    LazyColumn(
        modifier = modifier,
        state = lazyListState
    ) {
        items(chatHistoryResponse.size) { index ->
            ChatSection(
                textModifier = customTextModifier,
                response = chatHistoryResponse,
                index = index,
                onTextCopied = { copiedText ->
                    val annotatedString = AnnotatedString(copiedText)
                    clipboardManager.setText(annotatedString)
                }
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