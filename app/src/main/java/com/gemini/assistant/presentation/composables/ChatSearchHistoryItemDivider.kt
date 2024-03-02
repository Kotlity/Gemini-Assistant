package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R

@Composable
fun ChatSearchHistoryItemDivider() {
    CustomDivider(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen._2dp))
            .fillMaxWidth(),
        thickness = dimensionResource(id = R.dimen._1dp)
    )
}