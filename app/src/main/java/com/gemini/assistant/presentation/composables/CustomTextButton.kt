package com.gemini.assistant.presentation.composables

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    onTextButtonClick: () -> Unit,
    text: String,
    containerColor: Color,
    contentColor: Color
) {
    TextButton(
        modifier = modifier,
        onClick = onTextButtonClick,
        colors = ButtonDefaults.textButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        CustomText(
            text = text,
            textStyle = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}