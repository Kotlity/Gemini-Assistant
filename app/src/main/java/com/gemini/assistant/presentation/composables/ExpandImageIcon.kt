package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ExpandImageIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.AspectRatio,
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    onExpandIconClick: () -> Unit
) {
    Icon(
        modifier = modifier.clickable(onClick = onExpandIconClick),
        imageVector = imageVector,
        tint = iconColor,
        contentDescription = null
    )
}