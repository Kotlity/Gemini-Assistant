package com.gemini.assistant.utils.internet_connection

sealed class ConnectivityStatus {
    data class Available(val type: ConnectivityType): ConnectivityStatus()
    data object Lost: ConnectivityStatus()
    data object Unavailable: ConnectivityStatus()
}