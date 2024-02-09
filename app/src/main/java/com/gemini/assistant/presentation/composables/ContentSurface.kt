package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R

@Composable
fun ContentSurface(
    modifier: Modifier = Modifier,
    shapeDp: Dp = dimensionResource(id = R.dimen._35dp),
    shape: Shape = RoundedCornerShape(shapeDp),
    color: Color = MaterialTheme.colorScheme.surface,
    shadowElevation: Dp = dimensionResource(id = R.dimen._5dp),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        shadowElevation = shadowElevation,
        content = content
    )
}