package com.gemini.assistant.domain.connection_handler

import kotlinx.coroutines.flow.Flow

interface ConnectionHandler {

    fun isConnectedToTheInternet(): Flow<Boolean>
}