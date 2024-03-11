package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R
import com.gemini.assistant.utils.helpers.TopAppBarTitleProvider

@Composable
fun CustomTopAppBarTitle(
    horizontalSpace: Dp = dimensionResource(id = R.dimen._2dp),
    topAppBarTitleProvider: TopAppBarTitleProvider
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(dimensionResource(id = R.dimen._35dp)),
            painter = painterResource(id = R.drawable.gemini_logo),
            contentDescription = null
        )
        Text(
            text = topAppBarTitleProvider.topAppBarTitle(context),
            style = MaterialTheme.typography.titleMedium
        )
    }
}