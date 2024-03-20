package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ImagePickerIcon(
    modifier: Modifier = Modifier,
    isIconEnabled: Boolean = true,
    imageVector: ImageVector = Icons.Default.AddPhotoAlternate,
    imageVectorTint: Color = MaterialTheme.colorScheme.onBackground,
    onIconClick: () -> Unit
) {
    Icon(
        modifier = modifier.clickable(
            enabled = isIconEnabled,
            onClick = onIconClick
        ),
        imageVector = imageVector,
        tint = imageVectorTint,
        contentDescription = null
    )
}