package com.gemini.assistant.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CustomTopAppBarNavigationIcon(
    icon: ImageVector = Icons.Default.Menu,
    onIconClick: () -> Unit
) {
    IconButton(onClick = onIconClick) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}