package com.gemini.assistant.domain.usecases.search

import com.gemini.assistant.domain.search.SearchResponse
import javax.inject.Inject

class ChatHistoryResponseUseCase @Inject constructor(private val searchResponse: SearchResponse) {

    operator fun invoke() = searchResponse.chatHistoryResponse
}