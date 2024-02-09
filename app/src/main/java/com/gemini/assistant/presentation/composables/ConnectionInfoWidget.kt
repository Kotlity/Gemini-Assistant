package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.HMobiledata
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.gemini.assistant.R
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import com.gemini.assistant.utils.internet_connection.ConnectivityType

@Composable
fun ConnectionInfoWidget(
    modifier: Modifier = Modifier,
    connectivityStatus: ConnectivityStatus
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when(connectivityStatus) {
            is ConnectivityStatus.Available -> {
                when(connectivityStatus.type) {
                    ConnectivityType.Wi_Fi -> {
                        CustomText(
                            text = stringResource(id = R.string.wi_fi_internet_connection),
                            textStyle = MaterialTheme.typography.bodySmall.copy(
                                color = colorResource(id = R.color.green)
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.Wifi,
                            contentDescription = null,
                            tint = colorResource(id = R.color.green)
                        )
                    }
                    ConnectivityType.Mobile -> {
                        CustomText(
                            text = stringResource(id = R.string.mobile_internet_connection),
                            textStyle = MaterialTheme.typography.bodySmall.copy(
                                color = colorResource(id = R.color.green)
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.HMobiledata,
                            contentDescription = null,
                            tint = colorResource(id = R.color.green)
                        )
                    }
                    ConnectivityType.VPN -> {
                        CustomText(
                            text = stringResource(id = R.string.vpn_internet_connection),
                            textStyle = MaterialTheme.typography.bodySmall.copy(
                                color = colorResource(id = R.color.green)
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.VpnKey,
                            contentDescription = null,
                            tint = colorResource(id = R.color.green)
                        )
                    }
                    ConnectivityType.Undefined -> {
                        CustomText(
                            text = stringResource(id = R.string.undefined_internet_connection),
                            textStyle = MaterialTheme.typography.bodySmall.copy(
                                color = colorResource(id = R.color.yellow)
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.QuestionMark,
                            contentDescription = null,
                            tint = colorResource(id = R.color.yellow)
                        )
                    }
                }
            }
            ConnectivityStatus.Unavailable -> {
                CustomText(
                    text = stringResource(id = R.string.unavailable_internet_connection),
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onError
                    )
                )
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onError
                )
            }
            ConnectivityStatus.Lost -> {
                CustomText(
                    text = stringResource(id = R.string.lost_internet_connection),
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onError
                    )
                )
                Icon(
                    imageVector = Icons.Default.SignalWifiConnectedNoInternet4,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onError
                )
            }
        }
    }
}