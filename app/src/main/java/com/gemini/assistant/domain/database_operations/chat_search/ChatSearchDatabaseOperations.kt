package com.gemini.assistant.domain.database_operations.chat_search

import com.gemini.assistant.data.room.entities.ChatSearchEntity
import kotlinx.coroutines.flow.Flow

interface ChatSearchDatabaseOperations {

    suspend fun insertChatSearchEntity(chatSearchEntity: ChatSearchEntity)

    suspend fun deleteChatSearchEntity(chatSearchEntity: ChatSearchEntity)

    suspend fun deleteChatSearchOlderEntities()

    fun retrieveFiveLastChatSearchQueries(): Flow<List<ChatSearchEntity>>
}