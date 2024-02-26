package com.gemini.assistant.domain.usecases.chat_search_operations

import com.gemini.assistant.domain.database_operations.ChatSearchDatabaseOperations
import com.gemini.assistant.domain.model.ChatSearchModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrieveFiveLastChatSearchQueriesUseCase @Inject constructor(private val chatSearchDatabaseOperations: ChatSearchDatabaseOperations) {

    operator fun invoke(): Flow<List<ChatSearchModel>> = chatSearchDatabaseOperations
        .retrieveFiveLastChatSearchQueries().map { searchEntities ->
            searchEntities.map { entity ->
                entity.toSearchModel()
            }
        }
}