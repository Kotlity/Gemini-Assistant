package com.gemini.assistant.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gemini.assistant.utils.Constants

@Composable
fun ChatTextResponse(
    modifier: Modifier = Modifier,
    text: String,
    onTextCopied: ((String) -> Unit)? = null,
    textAlign: TextAlign = TextAlign.Start
) {
    CustomText(
        modifier = modifier,
        text = text,
        onTextCopied = onTextCopied,
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            fontSize = Constants._16sp,
            color = MaterialTheme.colorScheme.onBackground
        ),
        textAlign = textAlign
    )
}