package com.gemini.assistant.domain.usecases.search_operations

import com.gemini.assistant.domain.database_operations.SearchDatabaseOperations
import com.gemini.assistant.domain.model.SearchModel
import javax.inject.Inject

class InsertSearchUseCase @Inject constructor(private val searchDatabaseOperations: SearchDatabaseOperations) {

    suspend operator fun invoke(searchModel: SearchModel) = searchDatabaseOperations.insertSearchEntity(searchEntity = searchModel.toSearchEntity())
}