package com.gemini.assistant.domain.usecases.chat_search_operations

import com.gemini.assistant.domain.database_operations.chat_search.ChatSearchDatabaseOperations
import javax.inject.Inject

class DeleteChatSearchOlderUseCase @Inject constructor(private val chatSearchDatabaseOperations: ChatSearchDatabaseOperations) {

    suspend operator fun invoke() = chatSearchDatabaseOperations.deleteChatSearchOlderEntities()
}