package com.gemini.assistant.domain.usecases.search

import com.gemini.assistant.data.dto.SearchRequestDto
import com.gemini.assistant.data.dto.SearchResponseDto
import com.gemini.assistant.data.mappers.mapGeminiResult
import com.gemini.assistant.data.mappers.toSearchRequestDto
import com.gemini.assistant.domain.model.SearchRequestModel
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.domain.search.SearchResponse
import com.gemini.assistant.utils.GeminiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchResponseUseCase @Inject constructor(private val searchResponse: SearchResponse<SearchRequestDto, SearchResponseDto>) {

    operator fun invoke(searchRequestData: SearchRequestModel): Flow<GeminiResult<List<SearchResponseModel>>> =
        searchResponse.search(searchRequestData = searchRequestData.toSearchRequestDto()).map { it.mapGeminiResult() }
}