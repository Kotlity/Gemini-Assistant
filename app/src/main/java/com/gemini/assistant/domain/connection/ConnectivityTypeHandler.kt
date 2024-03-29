package com.gemini.assistant.domain.connection

import android.net.NetworkCapabilities
import com.gemini.assistant.utils.internet_connection.ConnectivityType

interface ConnectivityTypeHandler {

    fun retrieveConnectivityType(networkCapabilities: NetworkCapabilities): ConnectivityType
}