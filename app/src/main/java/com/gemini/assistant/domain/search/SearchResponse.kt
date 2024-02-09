package com.gemini.assistant.domain.search

import kotlinx.coroutines.flow.Flow

interface SearchResponse {

    fun search(searchText: String, searchImages: List<String>): Flow<String>
}