package com.gemini.assistant.presentation.composables

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current,
    textAlign: TextAlign = TextAlign.Start
) {

    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        textAlign = textAlign
    )
}

