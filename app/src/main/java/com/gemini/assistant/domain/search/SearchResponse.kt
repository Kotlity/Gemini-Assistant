package com.gemini.assistant.domain.search

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface SearchResponse {

    fun search(searchText: String, searchImages: List<Bitmap>? = null): Flow<String>
}