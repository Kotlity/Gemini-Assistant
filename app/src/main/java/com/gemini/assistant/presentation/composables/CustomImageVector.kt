package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomImageVector(
    modifier: Modifier = Modifier,
    imageVector: ImageVector
) {
    Image(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = null
    )
}