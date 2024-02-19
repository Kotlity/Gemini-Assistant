package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.gemini.assistant.R

@Composable
fun ChatUserSection(
    userInput: String,
    onTextCopied: ((String) -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        TitleSection(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen._3dp))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen._2dp), Alignment.End),
            isGeminiTitle = false,
            title = stringResource(id = R.string.userTitle),
            icon = painterResource(id = R.drawable.user_default_logo)
        )
        ChatTextResponse(
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen._7dp),
                top = dimensionResource(id = R.dimen._5dp),
                end = dimensionResource(id = R.dimen._5dp),
                bottom = dimensionResource(id = R.dimen._5dp)
            ),
            text = userInput,
            onTextCopied = onTextCopied,
            textAlign = TextAlign.End
        )
    }
}