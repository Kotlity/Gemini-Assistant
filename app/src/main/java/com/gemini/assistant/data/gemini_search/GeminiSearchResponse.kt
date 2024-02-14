package com.gemini.assistant.data.gemini_search

import android.content.Context
import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.domain.search.SearchResponse
import com.gemini.assistant.utils.Constants.ERROR
import com.gemini.assistant.utils.helpers.convertStringsToBitmaps
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeminiSearchResponse @Inject constructor(
    private val applicationContext: Context,
    private val geminiModel: GenerativeModel,
    private val appDispatcher: AppDispatcher
): SearchResponse {

    override fun search(searchText: String, searchImages: List<String>): Flow<String> {
        val searchContent = content {
            text(searchText)
//            if (searchImages.isNotEmpty()) {
//                val searchBitmaps = searchImages.convertStringsToBitmaps(applicationContext)
//                searchBitmaps.forEach { searchBitmap ->
//                    image(searchBitmap)
//                }
//            }
        }
        return geminiModel.generateContentStream(searchContent)
            .map { geminiResponse ->
                geminiResponse.text ?: ERROR
            }
            .catch { throwable ->
                throwable.localizedMessage
            }
            .flowOn(appDispatcher.io)
    }
}