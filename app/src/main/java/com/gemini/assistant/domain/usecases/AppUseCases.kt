package com.gemini.assistant.domain.usecases

import com.gemini.assistant.domain.usecases.connection.ConnectionHandlerUseCase
import com.gemini.assistant.domain.usecases.search.SearchResponseUseCase
import com.gemini.assistant.domain.usecases.search_operations.DeleteSearchUseCase
import com.gemini.assistant.domain.usecases.search_operations.InsertSearchUseCase
import com.gemini.assistant.domain.usecases.search_operations.RetrieveFiveLastSearchQueriesUseCase

data class AppUseCases(
    val connectionHandlerUseCase: ConnectionHandlerUseCase,
    val searchResponseUseCase: SearchResponseUseCase,
    val insertSearchUseCase: InsertSearchUseCase,
    val deleteSearchUseCase: DeleteSearchUseCase,
    val retrieveFiveLastSearchQueriesUseCase: RetrieveFiveLastSearchQueriesUseCase
)
