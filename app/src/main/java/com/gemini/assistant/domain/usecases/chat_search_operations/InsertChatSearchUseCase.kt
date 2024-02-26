package com.gemini.assistant.domain.usecases.chat_search_operations

import com.gemini.assistant.domain.database_operations.ChatSearchDatabaseOperations
import com.gemini.assistant.domain.model.ChatSearchModel
import javax.inject.Inject

class InsertChatSearchUseCase @Inject constructor(private val chatSearchDatabaseOperations: ChatSearchDatabaseOperations) {

    suspend operator fun invoke(chatSearchModel: ChatSearchModel) = chatSearchDatabaseOperations.insertChatSearchEntity(chatSearchEntity = chatSearchModel.toSearchEntity())
}