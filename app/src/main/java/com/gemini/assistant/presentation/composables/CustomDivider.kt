package com.gemini.assistant.presentation.composables

import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R

@Composable
fun CustomDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = dimensionResource(id = R.dimen._2dp),
    color: Color = MaterialTheme.colorScheme.tertiaryContainer
) {
    Divider(
        modifier = modifier,
        thickness = thickness,
        color = color
    )
}