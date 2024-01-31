package com.gemini.assistant.domain.search

import com.gemini.assistant.data.room.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchResponse {

    fun retrieveSearchResponse(searchEntity: SearchEntity): Flow<SearchEntity>
}