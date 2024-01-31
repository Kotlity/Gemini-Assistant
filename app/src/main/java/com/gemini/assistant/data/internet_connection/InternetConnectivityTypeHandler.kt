package com.gemini.assistant.data.internet_connection

import android.net.NetworkCapabilities
import com.gemini.assistant.domain.connection.ConnectivityTypeHandler
import com.gemini.assistant.utils.interner_connection.ConnectivityType
import javax.inject.Inject

class InternetConnectivityTypeHandler @Inject constructor(): ConnectivityTypeHandler {

    override fun retrieveConnectivityType(networkCapabilities: NetworkCapabilities): ConnectivityType {
        return when  {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> ConnectivityType.Wi_Fi
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> ConnectivityType.Mobile
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> ConnectivityType.VPN
            else -> ConnectivityType.Undefined
        }
    }
}