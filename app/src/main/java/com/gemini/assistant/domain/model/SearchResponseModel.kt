package com.gemini.assistant.domain.model

import android.graphics.Bitmap

data class SearchResponseModel(
    val searchResponse: String = "",
    val searchResponseImage: Bitmap? = null
)