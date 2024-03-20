package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._18sp

@Composable
fun ErrorSection(
    modifier: Modifier = Modifier,
    errorMessage: String,
    verticalSpace: Dp = dimensionResource(id = R.dimen._3dp),
    onRetryButtonClick: () -> Unit
) {
    ContentBox(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(verticalSpace, Alignment.CenterVertically)
        ) {
            CustomText(
                text = errorMessage,
                textStyle = MaterialTheme.typography.titleMedium.copy(
                    fontSize = _18sp,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center
            )
            ErrorButton(onErrorButtonClick = onRetryButtonClick)
        }
    }
}