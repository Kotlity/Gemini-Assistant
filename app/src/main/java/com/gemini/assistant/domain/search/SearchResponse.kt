package com.gemini.assistant.domain.search

import kotlinx.coroutines.flow.Flow

interface SearchResponse {

    val chatHistoryResponse: List<String>

    fun search(searchText: String, searchImages: List<String>): Flow<String>

}