package com.gemini.assistant.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DisabledByDefault
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.gemini.assistant.utils.Constants._500

@Composable
fun SearchTrailingIcon(
    enabled: Boolean = true,
    enabledIcon: ImageVector = Icons.Default.Send,
    disabledIcon: ImageVector = Icons.Default.DisabledByDefault,
    onSearchTrailingIconClick: () -> Unit
) {

    AnimatedContent(
        targetState = enabled,
        label = "",
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = _500))
                .togetherWith(fadeOut(animationSpec = tween(durationMillis = _500)))
        }
    ) { isEnabled ->
        if (isEnabled) DefaultIconButton(
            enabled,
            icon = enabledIcon,
            onIconButtonClick = onSearchTrailingIconClick
        )
        else DefaultIconButton(
            enabled,
            icon = disabledIcon,
            onIconButtonClick = onSearchTrailingIconClick
        )
    }
}

@Composable
private fun DefaultIconButton(
    enabled: Boolean,
    icon: ImageVector,
    onIconButtonClick: () -> Unit
) {
    IconButton(
        onClick = onIconButtonClick,
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}