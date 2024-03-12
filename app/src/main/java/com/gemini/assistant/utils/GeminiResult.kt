package com.gemini.assistant.utils

sealed class GeminiResult<T>(val data: T? = null, val loadingMessage: String? = null, val errorMessage: String? = null) {

    class Success<T>(data: T): GeminiResult<T>(data = data)
    class Error<T>(errorMessage: String): GeminiResult<T>(errorMessage = errorMessage)
    class Loading<T>(loadingMessage: String): GeminiResult<T>(loadingMessage = loadingMessage)
}