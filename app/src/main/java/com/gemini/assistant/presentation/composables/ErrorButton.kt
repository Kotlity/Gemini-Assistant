package com.gemini.assistant.presentation.composables

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.gemini.assistant.R
import com.gemini.assistant.utils.Constants._16sp

@Composable
fun ErrorButton(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.try_again_title),
    onErrorButtonClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onErrorButtonClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = _16sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}