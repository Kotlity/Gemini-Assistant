package com.gemini.assistant.domain.info

import android.graphics.Bitmap

data class SearchInput(
    val search: String,
    val bitmaps: List<Bitmap>? = null
)
