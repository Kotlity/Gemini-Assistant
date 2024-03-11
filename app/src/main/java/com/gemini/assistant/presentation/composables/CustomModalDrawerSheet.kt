package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun CustomModalDrawerSheet(
    modifier: Modifier = Modifier,
    shape: Shape = DrawerDefaults.shape,
    content: @Composable (ColumnScope.() -> Unit)
) {
    ModalDrawerSheet(
        modifier = modifier,
        drawerShape = shape,
        content = content
    )
}