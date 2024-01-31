package com.gemini.assistant.data.gemini_search_database_operations

import com.gemini.assistant.data.room.daos.GeminiSearchDao
import com.gemini.assistant.data.room.entities.SearchEntity
import com.gemini.assistant.domain.database_operations.SearchDatabaseOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GeminiSearchDatabaseOperations @Inject constructor(private val geminiSearchDao: GeminiSearchDao): SearchDatabaseOperations {

    override suspend fun insertSearchEntity(searchEntity: SearchEntity) {
        geminiSearchDao.insertSearchEntity(searchEntity = searchEntity)
    }

    override suspend fun deleteSearchEntity(searchEntity: SearchEntity) {
        geminiSearchDao.deleteSearchEntity(searchEntity = searchEntity)
    }

    override fun retrieveFiveLastSearchQueries(): Flow<List<SearchEntity>> {
        return geminiSearchDao.retrieveLastFiveSearchQueries()
    }
}