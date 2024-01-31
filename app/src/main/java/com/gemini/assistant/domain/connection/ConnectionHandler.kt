package com.gemini.assistant.domain.connection

import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ConnectionHandler {
    fun connectivityStatusHandler(): Flow<ConnectivityStatus>
}