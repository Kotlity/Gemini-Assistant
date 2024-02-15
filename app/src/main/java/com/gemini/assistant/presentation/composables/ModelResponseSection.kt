package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants

@Composable
fun ModelResponseSection(
    modifier: Modifier = Modifier,
    response: String
) {
    Column {
        CustomText(
            modifier = modifier,
            text = response,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                fontSize = Constants._16sp,
                color = MaterialTheme.colorScheme.secondary
            )
        )
        CustomDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._10dp))
        )
    }
}