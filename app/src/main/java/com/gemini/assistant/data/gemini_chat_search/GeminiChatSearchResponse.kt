package com.gemini.assistant.data.gemini_chat_search

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.di.annotations.GeminiProModel
import com.gemini.assistant.domain.chat_search.ChatSearchResponse
import com.gemini.assistant.utils.Constants.ERROR
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeminiChatSearchResponse @Inject constructor(
    @GeminiProModel
    private val geminiProModel: GenerativeModel,
    private val appDispatcher: AppDispatcher
): ChatSearchResponse {

    private val proChat by lazy {
        geminiProModel.startChat()
    }

    private fun retrieveChatHistoryResponse(): List<String> {
        return proChat.history
            .flatMap { content ->
                content.parts.map { part ->
                    part.asTextOrNull() ?: ""
                }
            }
    }

    override val chatHistoryResponse: List<String>
        get() = retrieveChatHistoryResponse()

    override fun chatSearch(searchText: String): Flow<String> {
        val searchContent = content("user") {
            text(searchText)
        }

        return proChat.sendMessageStream(searchContent)
            .map { chatResponse ->
                chatResponse.text ?: ERROR
            }
            .catch { throwable ->
                throwable.localizedMessage
            }
            .flowOn(appDispatcher.io)
    }
}