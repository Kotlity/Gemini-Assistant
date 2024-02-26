package com.gemini.assistant.domain.usecases.chat_search

import com.gemini.assistant.domain.chat_search.ChatSearchResponse
import javax.inject.Inject

class ChatHistoryResponseUseCase @Inject constructor(private val chatSearchResponse: ChatSearchResponse) {

    operator fun invoke() = chatSearchResponse.chatHistoryResponse
}