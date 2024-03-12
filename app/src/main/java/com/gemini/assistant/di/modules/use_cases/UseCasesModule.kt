package com.gemini.assistant.di.modules.use_cases

import com.gemini.assistant.data.dto.SearchRequestDto
import com.gemini.assistant.data.dto.SearchResponseDto
import com.gemini.assistant.di.modules.connection.ConnectionModule
import com.gemini.assistant.di.modules.gemini.chat_search.GeminiChatSearchResponseModule
import com.gemini.assistant.di.modules.chat_search_operations.GeminiChatSearchDatabaseOperationsModule
import com.gemini.assistant.di.modules.gemini.search.GeminiSearchResponseModule
import com.gemini.assistant.di.modules.user_photo_operations.UserPhotoDatabaseOperationsModule
import com.gemini.assistant.domain.connection.ConnectionHandler
import com.gemini.assistant.domain.database_operations.chat_search.ChatSearchDatabaseOperations
import com.gemini.assistant.domain.chat_search.ChatSearchResponse
import com.gemini.assistant.domain.database_operations.user_photo.UserPhotoDatabaseOperations
import com.gemini.assistant.domain.search.SearchResponse
import com.gemini.assistant.domain.usecases.AppUseCases
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(
    includes = [
        ConnectionModule::class,
        GeminiChatSearchResponseModule::class,
        GeminiSearchResponseModule::class,
        GeminiChatSearchDatabaseOperationsModule::class,
        UserPhotoDatabaseOperationsModule::class
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
    fun provideSearchResponseUseCase(searchResponse: SearchResponse<SearchRequestDto, SearchResponseDto>) = SearchResponseUseCase(searchResponse = searchResponse)

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
    fun provideInsertUserPhotoUseCase(userPhotoDatabaseOperations: UserPhotoDatabaseOperations) = InsertUserPhotoUseCase(userPhotoDatabaseOperations = userPhotoDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideRetrieveUserPhotoPathUseCase(userPhotoDatabaseOperations: UserPhotoDatabaseOperations) = RetrieveUserPhotoPathUseCase(userPhotoDatabaseOperations = userPhotoDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideAppUseCases(
        connectionHandlerUseCase: ConnectionHandlerUseCase,
        chatHistoryResponseUseCase: ChatHistoryResponseUseCase,
        chatSearchResponseUseCase: ChatSearchResponseUseCase,
        searchResponseUseCase: SearchResponseUseCase,
        insertChatSearchUseCase: InsertChatSearchUseCase,
        deleteChatSearchUseCase: DeleteChatSearchUseCase,
        deleteChatSearchOlderUseCase: DeleteChatSearchOlderUseCase,
        retrieveFiveLastChatSearchQueriesUseCase: RetrieveFiveLastChatSearchQueriesUseCase,
        insertUserPhotoUseCase: InsertUserPhotoUseCase,
        retrieveUserPhotoPathUseCase: RetrieveUserPhotoPathUseCase
    ) = AppUseCases(
        connectionHandlerUseCase = connectionHandlerUseCase,
        chatHistoryResponseUseCase = chatHistoryResponseUseCase,
        chatSearchResponseUseCase = chatSearchResponseUseCase,
        searchResponseUseCase = searchResponseUseCase,
        insertChatSearchUseCase = insertChatSearchUseCase,
        deleteChatSearchUseCase = deleteChatSearchUseCase,
        deleteChatSearchOlderUseCase = deleteChatSearchOlderUseCase,
        retrieveFiveLastChatSearchQueriesUseCase = retrieveFiveLastChatSearchQueriesUseCase,
        insertUserPhotoUseCase = insertUserPhotoUseCase,
        retrieveUserPhotoPathUseCase = retrieveUserPhotoPathUseCase
    )
}