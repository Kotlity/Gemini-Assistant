package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R

@Composable
fun ChatSearchHistoryItemDivider(
    searchHistoryItemWidth: Dp
) {
    CustomDivider(
        modifier = Modifier
            .width(searchHistoryItemWidth - dimensionResource(id = R.dimen._5dp)),
        thickness = dimensionResource(id = R.dimen._1dp)
    )
}