package com.gemini.assistant.data.dto

import android.graphics.Bitmap

data class SearchResponseDto(
    val text: String? = null,
    val image: Bitmap? = null
)