package com.gemini.assistant.domain.search

import com.gemini.assistant.utils.GeminiResult
import kotlinx.coroutines.flow.Flow

interface SearchResponse<I, O> {

    fun search(searchRequestData: I): Flow<GeminiResult<List<O>>>
}