package com.gemini.assistant.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CustomAsyncImage(
    modifier: Modifier = Modifier,
    data: Any?
) {
    val context = LocalContext.current

    val imageRequest = ImageRequest
        .Builder(context)
        .data(data)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = null
    )
}