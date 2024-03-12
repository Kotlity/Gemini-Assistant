package com.gemini.assistant.data.dto

import android.graphics.Bitmap

data class SearchRequestDto(
    val searchRequest: String,
    val searchRequestImages: List<Bitmap>? = null
)
