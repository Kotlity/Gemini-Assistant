package com.gemini.assistant.utils.interner_connection

sealed class ConnectivityStatus {
    data class Available(val type: ConnectivityType): ConnectivityStatus()
    data object Lost: ConnectivityStatus()
    data object Unavailable: ConnectivityStatus()
}