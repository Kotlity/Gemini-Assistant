package com.gemini.assistant.di.modules.use_cases

import com.gemini.assistant.di.modules.connection.ConnectionModule
import com.gemini.assistant.di.modules.gemini.chat_search.GeminiChatSearchResponseModule
import com.gemini.assistant.di.modules.chat_search_operations.GeminiChatSearchDatabaseOperationsModule
import com.gemini.assistant.domain.connection.ConnectionHandler
import com.gemini.assistant.domain.database_operations.ChatSearchDatabaseOperations
import com.gemini.assistant.domain.chat_search.ChatSearchResponse
import com.gemini.assistant.domain.usecases.AppUseCases
import com.gemini.assistant.domain.usecases.connection.ConnectionHandlerUseCase
import com.gemini.assistant.domain.usecases.chat_search.ChatHistoryResponseUseCase
import com.gemini.assistant.domain.usecases.chat_search.ChatSearchResponseUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.DeleteChatSearchOlderUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.DeleteChatSearchUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.InsertChatSearchUseCase
import com.gemini.assistant.domain.usecases.chat_search_operations.RetrieveFiveLastChatSearchQueriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(
    includes = [
        ConnectionModule::class,
        GeminiChatSearchResponseModule::class,
        GeminiChatSearchDatabaseOperationsModule::class
    ]
)
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideConnectionHandlerUseCase(connectionHandler: ConnectionHandler) = ConnectionHandlerUseCase(connectionHandler = connectionHandler)

    @Provides
    @ViewModelScoped
    fun provideChatHistoryResponseUseCase(chatSearchResponse: ChatSearchResponse) = ChatHistoryResponseUseCase(chatSearchResponse = chatSearchResponse)

    @Provides
    @ViewModelScoped
    fun provideChatSearchResponseUseCase(chatSearchResponse: ChatSearchResponse) = ChatSearchResponseUseCase(chatSearchResponse = chatSearchResponse)

    @Provides
    @ViewModelScoped
    fun provideInsertChatSearchUseCase(chatSearchDatabaseOperations: ChatSearchDatabaseOperations) = InsertChatSearchUseCase(chatSearchDatabaseOperations = chatSearchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideDeleteChatSearchUseCase(chatSearchDatabaseOperations: ChatSearchDatabaseOperations) = DeleteChatSearchUseCase(chatSearchDatabaseOperations = chatSearchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideDeleteChatSearchOlderUseCase(chatSearchDatabaseOperations: ChatSearchDatabaseOperations) = DeleteChatSearchOlderUseCase(chatSearchDatabaseOperations = chatSearchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideRetrieveFiveLastChatSearchQueriesUseCase(chatSearchDatabaseOperations: ChatSearchDatabaseOperations) = RetrieveFiveLastChatSearchQueriesUseCase(chatSearchDatabaseOperations = chatSearchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideAppUseCases(
        connectionHandlerUseCase: ConnectionHandlerUseCase,
        chatHistoryResponseUseCase: ChatHistoryResponseUseCase,
        chatSearchResponseUseCase: ChatSearchResponseUseCase,
        insertChatSearchUseCase: InsertChatSearchUseCase,
        deleteChatSearchUseCase: DeleteChatSearchUseCase,
        deleteChatSearchOlderUseCase: DeleteChatSearchOlderUseCase,
        retrieveFiveLastChatSearchQueriesUseCase: RetrieveFiveLastChatSearchQueriesUseCase
    ) = AppUseCases(
        connectionHandlerUseCase = connectionHandlerUseCase,
        chatHistoryResponseUseCase = chatHistoryResponseUseCase,
        chatSearchResponseUseCase = chatSearchResponseUseCase,
        insertChatSearchUseCase = insertChatSearchUseCase,
        deleteChatSearchUseCase = deleteChatSearchUseCase,
        deleteChatSearchOlderUseCase = deleteChatSearchOlderUseCase,
        retrieveFiveLastChatSearchQueriesUseCase = retrieveFiveLastChatSearchQueriesUseCase
    )
}