package com.gemini.assistant.domain.usecases

import com.gemini.assistant.domain.usecases.connection.ConnectionHandlerUseCase
import com.gemini.assistant.domain.usecases.chat_search.ChatHistoryResponseUseCase
import com.gemini.assistant.domain.usecases.chat_search.ChatSearchResponseUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.DeleteChatSearchOlderUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.DeleteChatSearchUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.InsertChatSearchUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.RetrieveFiveLastChatSearchQueriesUseCase
import com.gemini.assistant.domain.usecases.search.SearchResponseUseCase
import com.gemini.assistant.domain.usecases.user_photo_operations.InsertUserPhotoUseCase
import com.gemini.assistant.domain.usecases.user_photo_operations.RetrieveUserPhotoPathUseCase

data class AppUseCases(
    val connectionHandlerUseCase: ConnectionHandlerUseCase,
    val chatHistoryResponseUseCase: ChatHistoryResponseUseCase,
    val chatSearchResponseUseCase: ChatSearchResponseUseCase,
    val searchResponseUseCase: SearchResponseUseCase,
    val insertChatSearchUseCase: InsertChatSearchUseCase,
    val deleteChatSearchUseCase: DeleteChatSearchUseCase,
    val deleteChatSearchOlderUseCase: DeleteChatSearchOlderUseCase,
    val retrieveFiveLastChatSearchQueriesUseCase: RetrieveFiveLastChatSearchQueriesUseCase,
    val insertUserPhotoUseCase: InsertUserPhotoUseCase,
    val retrieveUserPhotoPathUseCase: RetrieveUserPhotoPathUseCase
)