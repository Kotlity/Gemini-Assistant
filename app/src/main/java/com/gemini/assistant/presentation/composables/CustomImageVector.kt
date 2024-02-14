package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Computer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomImageVector(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    colorFilter: ColorFilter? = null
) {
    Image(
        modifier = modifier,
        imageVector = imageVector,
        colorFilter = colorFilter,
        contentDescription = null
    )
}