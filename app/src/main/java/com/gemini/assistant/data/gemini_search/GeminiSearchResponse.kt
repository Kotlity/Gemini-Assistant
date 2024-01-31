package com.gemini.assistant.data.gemini_search

import com.gemini.assistant.data.room.entities.SearchEntity
import com.gemini.assistant.domain.search.SearchResponse
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeminiSearchResponse @Inject constructor(private val geminiModel: GenerativeModel): SearchResponse {

    override fun retrieveSearchResponse(searchEntity: SearchEntity): Flow<SearchEntity> {

    }
}