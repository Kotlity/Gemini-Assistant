package com.gemini.assistant.data.internet_connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.gemini.assistant.domain.connection.ConnectionHandler
import com.gemini.assistant.domain.connection.ConnectivityTypeHandler
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InternetConnectionHandler @Inject constructor(
    private val context: Context,
    private val connectivityTypeHandler: ConnectivityTypeHandler
): ConnectionHandler {

    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private val networkRequest = NetworkRequest
        .Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    override fun connectivityStatusHandler(): Flow<ConnectivityStatus> {
        return callbackFlow {

            val networkCallback = object: ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    val networkCapabilities = connectivityManager.getNetworkCapabilities(network)!!
                    val connectivityType = connectivityTypeHandler.retrieveConnectivityType(networkCapabilities)
                    trySend(ConnectivityStatus.Available(type = connectivityType))
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(ConnectivityStatus.Lost)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(ConnectivityStatus.Unavailable)
                }

                override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val connectivityType = connectivityTypeHandler.retrieveConnectivityType(networkCapabilities)
                    trySend(ConnectivityStatus.Available(type = connectivityType))
                }
            }

            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }
}