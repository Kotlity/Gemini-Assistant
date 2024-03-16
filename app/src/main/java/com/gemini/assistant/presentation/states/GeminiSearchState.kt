package com.gemini.assistant.presentation.states

import com.gemini.assistant.domain.model.SearchRequestModel
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.utils.GeminiResult

data class GeminiSearchState(
    val searchRequestModel: SearchRequestModel = SearchRequestModel(),
    val searchResponseModel: GeminiResult<SearchResponseModel> = GeminiResult.Undefined(),
    val visiblePermissionDialogQueue: List<String> = emptyList()
)
