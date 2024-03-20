package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DeleteImageIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.Close,
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    onDeleteIconClick: () -> Unit
) {
    Icon(
        modifier = modifier.clickable(onClick = onDeleteIconClick),
        imageVector = imageVector,
        tint = iconColor,
        contentDescription = null
    )
}