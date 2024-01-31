package com.gemini.assistant.domain.usecases.connection

import com.gemini.assistant.domain.connection.ConnectionHandler
import javax.inject.Inject

class ConnectionHandlerUseCase @Inject constructor(private val connectionHandler: ConnectionHandler) {

    operator fun invoke() = connectionHandler.connectivityStatusHandler()
}