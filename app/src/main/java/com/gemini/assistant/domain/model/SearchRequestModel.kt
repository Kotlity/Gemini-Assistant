package com.gemini.assistant.domain.model

import android.graphics.Bitmap

data class SearchRequestModel(
    val searchRequest: String = "",
    val searchRequestImages: List<Bitmap> = emptyList()
)
