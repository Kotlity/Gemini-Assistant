package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gemini.assistant.R

@Composable
fun ChatSection(
    textModifier: Modifier = Modifier,
    response: List<String>,
    index: Int,
    onTextCopied: ((String) -> Unit)? = null
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (index % 2 == 0) {
            ChatUserSection(
                userInput = response[index],
                onTextCopied = onTextCopied
            )
        } else {
            TitleSection(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen._3dp))
                    .fillMaxWidth(),
                title = stringResource(id = R.string.geminiTitle),
                icon = painterResource(id = R.drawable.gemini_logo)
            )
            ChatTextResponse(
                modifier = textModifier,
                text = response[index],
                onTextCopied = onTextCopied
            )
        }
    }
}