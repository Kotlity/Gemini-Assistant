package com.gemini.assistant.domain.usecases.search

import com.gemini.assistant.domain.search.SearchResponse
import javax.inject.Inject

class SearchResponseUseCase @Inject constructor(private val searchResponse: SearchResponse) {

    operator fun invoke(searchText: String, searchImages: List<String>) = searchResponse.search(searchText = searchText, searchImages = searchImages)
}