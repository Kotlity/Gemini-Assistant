package com.gemini.assistant.domain.database_operations

import com.gemini.assistant.data.room.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchDatabaseOperations {

    suspend fun insertSearchEntity(searchEntity: SearchEntity)

    suspend fun deleteSearchEntity(searchEntity: SearchEntity)

    fun retrieveFiveLastSearchQueries(): Flow<List<SearchEntity>>
}