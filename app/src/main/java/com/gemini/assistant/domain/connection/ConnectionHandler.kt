package com.gemini.assistant.domain.connection

import com.gemini.assistant.utils.interner_connection.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ConnectionHandler {
    fun isConnectedToTheInternet(): Flow<ConnectivityStatus>
}