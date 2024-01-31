package com.gemini.assistant.domain.usecases.search

import android.graphics.Bitmap
import com.gemini.assistant.domain.search.SearchResponse
import javax.inject.Inject

class SearchResponseUseCase @Inject constructor(private val searchResponse: SearchResponse) {

    operator fun invoke(searchText: String, searchImages: List<Bitmap>? = null) = searchResponse.search(searchText = searchText, searchImages = searchImages)
}