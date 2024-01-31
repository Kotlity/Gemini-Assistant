package com.gemini.assistant.di.modules.use_cases

import com.gemini.assistant.di.modules.connection.ConnectionModule
import com.gemini.assistant.di.modules.gemini.search.GeminiSearchResponseModule
import com.gemini.assistant.di.modules.search_operations.GeminiSearchDatabaseOperationsModule
import com.gemini.assistant.domain.connection.ConnectionHandler
import com.gemini.assistant.domain.database_operations.SearchDatabaseOperations
import com.gemini.assistant.domain.search.SearchResponse
import com.gemini.assistant.domain.usecases.AppUseCases
import com.gemini.assistant.domain.usecases.connection.ConnectionHandlerUseCase
import com.gemini.assistant.domain.usecases.search.SearchResponseUseCase
import com.gemini.assistant.domain.usecases.search_operations.DeleteSearchUseCase
import com.gemini.assistant.domain.usecases.search_operations.InsertSearchUseCase
import com.gemini.assistant.domain.usecases.search_operations.RetrieveFiveLastSearchQueriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module(
    includes = [
        ConnectionModule::class,
        GeminiSearchResponseModule::class,
        GeminiSearchDatabaseOperationsModule::class
    ]
)
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Provides
    @ViewModelScoped
    fun provideConnectionHandlerUseCase(connectionHandler: ConnectionHandler) = ConnectionHandlerUseCase(connectionHandler)

    @Provides
    @ViewModelScoped
    fun provideSearchResponseUseCase(searchResponse: SearchResponse) = SearchResponseUseCase(searchResponse = searchResponse)

    @Provides
    @ViewModelScoped
    fun provideInsertSearchUseCase(searchDatabaseOperations: SearchDatabaseOperations) = InsertSearchUseCase(searchDatabaseOperations = searchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideDeleteSearchUseCase(searchDatabaseOperations: SearchDatabaseOperations) = DeleteSearchUseCase(searchDatabaseOperations = searchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideRetrieveFiveLastSearchQueriesUseCase(searchDatabaseOperations: SearchDatabaseOperations) = RetrieveFiveLastSearchQueriesUseCase(searchDatabaseOperations = searchDatabaseOperations)

    @Provides
    @ViewModelScoped
    fun provideAppUseCases(
        connectionHandlerUseCase: ConnectionHandlerUseCase,
        searchResponseUseCase: SearchResponseUseCase,
        insertSearchUseCase: InsertSearchUseCase,
        deleteSearchUseCase: DeleteSearchUseCase,
        retrieveFiveLastSearchQueriesUseCase: RetrieveFiveLastSearchQueriesUseCase
    ) = AppUseCases(
        connectionHandlerUseCase = connectionHandlerUseCase,
        searchResponseUseCase = searchResponseUseCase,
        insertSearchUseCase = insertSearchUseCase,
        deleteSearchUseCase = deleteSearchUseCase,
        retrieveFiveLastSearchQueriesUseCase = retrieveFiveLastSearchQueriesUseCase
    )
}