package com.gemini.assistant.domain.chat_search

import kotlinx.coroutines.flow.Flow

interface ChatSearchResponse {

    val chatHistoryResponse: List<String>

    fun chatSearch(searchText: String): Flow<String>

}