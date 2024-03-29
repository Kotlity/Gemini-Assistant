package com.gemini.assistant.data.dto

import android.graphics.Bitmap

data class SearchResponseDto(
    val searchResponse: String,
    val searchResponseImage: Bitmap? = null
)