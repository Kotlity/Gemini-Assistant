package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.GMobiledata
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._14sp
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
                        ConnectivityResponseText(response = stringResource(id = R.string.wi_fi_internet_connection))
                        Icon(
                            imageVector = Icons.Default.Wifi,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    ConnectivityType.Mobile -> {
                        ConnectivityResponseText(response = stringResource(id = R.string.mobile_internet_connection))
                        Icon(
                            imageVector = Icons.Default.GMobiledata,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    ConnectivityType.Undefined -> {
                        ConnectivityResponseText(response = stringResource(id = R.string.undefined_internet_connection))
                        Icon(
                            imageVector = Icons.Default.QuestionMark,
                            contentDescription = null,
                            tint = colorResource(id = R.color.yellow)
                        )
                    }
                }
            }
            ConnectivityStatus.Unavailable -> {
                ConnectivityResponseText(
                    response = stringResource(id = R.string.unavailable_internet_connection),
                    color = MaterialTheme.colorScheme.error
                )
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
            ConnectivityStatus.Lost -> {
                ConnectivityResponseText(
                    response = stringResource(id = R.string.lost_internet_connection),
                    color = MaterialTheme.colorScheme.error
                )
                Icon(
                    imageVector = Icons.Default.SignalWifiConnectedNoInternet4,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun ConnectivityResponseText(
    response: String,
    color: Color = MaterialTheme.colorScheme.primary
) {
    CustomText(
        text = response,
        textStyle = MaterialTheme.typography.bodySmall.copy(
            fontSize = _14sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    )
}