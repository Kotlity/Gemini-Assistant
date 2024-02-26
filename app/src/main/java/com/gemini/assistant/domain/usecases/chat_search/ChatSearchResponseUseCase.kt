package com.gemini.assistant.domain.usecases.chat_search

import com.gemini.assistant.domain.chat_search.ChatSearchResponse
import javax.inject.Inject

class ChatSearchResponseUseCase @Inject constructor(private val chatSearchResponse: ChatSearchResponse) {

    operator fun invoke(searchText: String) = chatSearchResponse.chatSearch(searchText = searchText)
}