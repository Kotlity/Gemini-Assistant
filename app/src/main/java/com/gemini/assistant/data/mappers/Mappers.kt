package com.gemini.assistant.data.mappers

import com.gemini.assistant.data.dto.SearchRequestDto
import com.gemini.assistant.data.dto.SearchResponseDto
import com.gemini.assistant.data.room.entities.ChatSearchEntity
import com.gemini.assistant.data.room.entities.UserPhotoEntity
import com.gemini.assistant.domain.model.ChatSearchModel
import com.gemini.assistant.domain.model.SearchRequestModel
import com.gemini.assistant.domain.model.SearchResponseModel
import com.gemini.assistant.domain.model.UserPhotoModel
import com.gemini.assistant.utils.Constants.LOADING_MESSAGE
import com.gemini.assistant.utils.Constants.UNKNOWN_ERROR
import com.gemini.assistant.utils.GeminiResult

fun ChatSearchEntity.toSearchModel(): ChatSearchModel {
    return ChatSearchModel(
        search = search
    )
}

fun ChatSearchModel.toSearchEntity(): ChatSearchEntity {
    return ChatSearchEntity(
        search = search
    )
}

fun UserPhotoModel.toUserPhotoEntity(): UserPhotoEntity {
    return UserPhotoEntity(
        userPhotoPath = userPhotoPath
    )
}

fun SearchResponseDto.toSearchResponseModel(): SearchResponseModel {
    return SearchResponseModel(
        searchResponse = searchResponse,
        searchResponseImage = searchResponseImage
    )
}

fun SearchRequestModel.toSearchRequestDto(): SearchRequestDto {
    return SearchRequestDto(
        searchRequest = searchRequest,
        searchRequestImages = searchRequestImages
    )
}

fun GeminiResult<List<SearchResponseDto>>.mapGeminiResult(): GeminiResult<List<SearchResponseModel>> {
    return when (this) {
        is GeminiResult.Success -> GeminiResult.Success(data = data?.map { it.toSearchResponseModel() } ?: emptyList())
        is GeminiResult.Error -> GeminiResult.Error(errorMessage = errorMessage ?: UNKNOWN_ERROR)
        is GeminiResult.Loading -> GeminiResult.Loading(loadingMessage = loadingMessage ?: LOADING_MESSAGE)
        is GeminiResult.Undefined -> GeminiResult.Undefined()
    }
}

fun GeminiResult<List<SearchResponseModel>>.updateGeminiResult(): GeminiResult<SearchResponseModel> {
    return when (this) {
        is GeminiResult.Error -> GeminiResult.Error(errorMessage = errorMessage ?: UNKNOWN_ERROR)
        is GeminiResult.Loading -> GeminiResult.Loading(loadingMessage = loadingMessage ?: LOADING_MESSAGE)
        is GeminiResult.Success -> GeminiResult.Success(data = data?.first() ?: SearchResponseModel())
        is GeminiResult.Undefined -> GeminiResult.Undefined()
    }
}