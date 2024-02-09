package com.gemini.assistant.data.gemini_search

import android.content.Context
import com.gemini.assistant.domain.search.SearchResponse
import com.gemini.assistant.utils.Constants.ERROR
import com.gemini.assistant.utils.helpers.convertStringsToBitmaps
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GeminiSearchResponse @Inject constructor(private val applicationContext: Context, private val geminiModel: GenerativeModel): SearchResponse {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun search(searchText: String, searchImages: List<String>): Flow<String> {
        val searchContent = content {
            text(searchText)
            if (searchImages.isNotEmpty()) {
                val searchBitmaps = searchImages.convertStringsToBitmaps(applicationContext)
                searchBitmaps.forEach { searchBitmap ->
                    image(searchBitmap)
                }
//                searchImages.forEach { searchImage ->
//                    image(searchImage)
//                }
            }
        }
        return geminiModel.generateContentStream(searchContent)
            .flatMapConcat { response ->
                flowOf(response.text ?: ERROR)
            }
            .catch { throwable ->
                throwable.localizedMessage
            }
            .flowOn(Dispatchers.IO)
    }
}