package com.gemini.assistant.data.gemini_chat_search_database_operations

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.data.room.daos.GeminiChatSearchDao
import com.gemini.assistant.data.room.entities.ChatSearchEntity
import com.gemini.assistant.domain.database_operations.ChatSearchDatabaseOperations
import com.gemini.assistant.utils.helpers.onCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GeminiChatSearchDatabaseOperations @Inject constructor(
    private val geminiChatSearchDao: GeminiChatSearchDao,
    private val appDispatcher: AppDispatcher
): ChatSearchDatabaseOperations {

    override suspend fun insertChatSearchEntity(chatSearchEntity: ChatSearchEntity) {
        onCoroutineContext {
            geminiChatSearchDao.insertChatSearchEntity(chatSearchEntity = chatSearchEntity)
        }
    }

    override suspend fun deleteChatSearchEntity(chatSearchEntity: ChatSearchEntity) {
        onCoroutineContext {
            geminiChatSearchDao.deleteChatSearchEntity(chatSearchEntity = chatSearchEntity)
        }
    }

    override suspend fun deleteChatSearchOlderEntities() {
        onCoroutineContext {
            geminiChatSearchDao.deleteChatSearchOlderEntities()
        }
    }

    override fun retrieveFiveLastChatSearchQueries(): Flow<List<ChatSearchEntity>> {
        return geminiChatSearchDao
            .retrieveLastFiveChatSearchQueries()
            .flowOn(appDispatcher.io)
    }
}