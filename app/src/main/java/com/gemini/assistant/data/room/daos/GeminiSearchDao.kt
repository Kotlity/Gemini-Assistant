package com.gemini.assistant.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gemini.assistant.data.room.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeminiSearchDao {

    @Insert(entity = SearchEntity::class)
    suspend fun insertSearchEntity(searchEntity: SearchEntity)

    @Delete(entity = SearchEntity::class)
    suspend fun deleteSearchEntity(searchEntity: SearchEntity)

    @Query("SELECT * FROM SearchEntity ORDER BY id DESC LIMIT 5")
    fun retrieveLastFiveSearchQueries(): Flow<SearchEntity>
}