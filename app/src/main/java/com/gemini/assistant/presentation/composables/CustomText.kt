package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    onTextCopied: ((String) -> Unit)? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign = TextAlign.Start
) {

    Text(
        modifier = modifier
            .combinedClickable(
                onLongClick = {
                    if (onTextCopied != null) {
                        onTextCopied(text)
                    }
                },
                onClick = {}
            ),
        text = text,
        style = textStyle,
        textAlign = textAlign
    )
}

