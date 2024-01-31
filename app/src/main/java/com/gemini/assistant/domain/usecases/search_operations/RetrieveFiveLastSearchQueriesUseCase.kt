package com.gemini.assistant.domain.usecases.search_operations

import com.gemini.assistant.domain.database_operations.SearchDatabaseOperations
import com.gemini.assistant.domain.info.SearchInput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrieveFiveLastSearchQueriesUseCase @Inject constructor(private val searchDatabaseOperations: SearchDatabaseOperations) {

    operator fun invoke(): Flow<List<SearchInput>> = searchDatabaseOperations
        .retrieveFiveLastSearchQueries().map { searchEntities ->
            searchEntities.map { entity ->
                entity.toSearchInput()
            }
        }
}