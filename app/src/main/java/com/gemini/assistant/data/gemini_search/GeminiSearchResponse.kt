package com.gemini.assistant.data.gemini_search

import com.gemini.assistant.data.dispatchers.AppDispatcher
import com.gemini.assistant.data.dto.SearchRequestDto
import com.gemini.assistant.data.dto.SearchResponseDto
import com.gemini.assistant.di.annotations.GeminiProModel
import com.gemini.assistant.di.annotations.GeminiProVisionModel
import com.gemini.assistant.domain.search.SearchResponse
import com.gemini.assistant.utils.Constants.LOADING_MESSAGE
import com.gemini.assistant.utils.Constants.UNKNOWN_ERROR
import com.gemini.assistant.utils.GeminiResult
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.asImageOrNull
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GeminiSearchResponse @Inject constructor(
    @GeminiProModel
    private val geminiProModel: GenerativeModel,
    @GeminiProVisionModel
    private val geminiProVisionModel: GenerativeModel,
    private val appDispatcher: AppDispatcher
): SearchResponse<SearchRequestDto, SearchResponseDto> {

    private fun GenerateContentResponse.retrieveModelResponse(): List<SearchResponseDto> {
        return candidates.filter { candidate ->
            candidate.content.role == "model"
        }
            .flatMap { modelCandidate ->
                modelCandidate.content.parts.map { modelPart ->
                    val text = modelPart.asTextOrNull() ?: ""
                    val image = modelPart.asImageOrNull()
                    SearchResponseDto(searchResponse = text, searchResponseImage = image)
                }
            }
    }

    override fun search(searchRequestData: SearchRequestDto): Flow<GeminiResult<List<SearchResponseDto>>> {
        val isImagesPicked = searchRequestData.searchRequestImages?.isNotEmpty()
        val searchContent = content("user") {
            text(searchRequestData.searchRequest)
            if (isImagesPicked == true) {
                searchRequestData.searchRequestImages.forEach { bitmap ->
                    image(bitmap)
                }
            }
        }

        return flow {
            emit(GeminiResult.Loading(loadingMessage = LOADING_MESSAGE))

            val response = if (isImagesPicked == true) geminiProVisionModel.generateContent(searchContent)
            else geminiProModel.generateContent(searchContent)


            val result = response.retrieveModelResponse()
            emit(GeminiResult.Success(data = result))
        }
            .catch { throwable ->
                emit(GeminiResult.Error(errorMessage = throwable.message ?: UNKNOWN_ERROR))
            }
            .flowOn(appDispatcher.io)
    }

}