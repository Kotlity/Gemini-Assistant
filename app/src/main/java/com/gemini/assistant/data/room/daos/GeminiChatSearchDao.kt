package com.gemini.assistant.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gemini.assistant.data.room.entities.ChatSearchEntity
import com.gemini.assistant.utils.Constants.MAX_SEARCH_QUERIES
import kotlinx.coroutines.flow.Flow

@Dao
interface GeminiChatSearchDao {

    @Insert(entity = ChatSearchEntity::class)
    suspend fun insertChatSearchEntity(chatSearchEntity: ChatSearchEntity)

    @Delete(entity = ChatSearchEntity::class)
    suspend fun deleteChatSearchEntity(chatSearchEntity: ChatSearchEntity)

    @Query("DELETE FROM ChatSearchEntity WHERE id NOT IN (SELECT id FROM ChatSearchEntity ORDER BY id DESC LIMIT $MAX_SEARCH_QUERIES)")
    suspend fun deleteChatSearchOlderEntities()

    @Query("SELECT * FROM ChatSearchEntity ORDER BY id DESC LIMIT $MAX_SEARCH_QUERIES")
    fun retrieveLastFiveChatSearchQueries(): Flow<List<ChatSearchEntity>>
}