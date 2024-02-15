package com.gemini.assistant.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gemini.assistant.R
import com.gemini.assistant.presentation.composables.ChatLazyColumn
import com.gemini.assistant.presentation.composables.ConnectionInfoWidget
import com.gemini.assistant.presentation.composables.ContentSurface
import com.gemini.assistant.presentation.composables.CustomDivider
import com.gemini.assistant.presentation.composables.SearchTextField
import com.gemini.assistant.presentation.composables.WelcomeWidget
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiSearchState
import com.gemini.assistant.utils.Constants._02f
import com.gemini.assistant.utils.Constants._05f
import com.gemini.assistant.utils.Constants._08f
import com.gemini.assistant.utils.Constants._16sp
import com.gemini.assistant.utils.Constants._18sp
import com.gemini.assistant.utils.helpers.isKeyboardOpen
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import kotlinx.coroutines.flow.StateFlow

@Composable
fun GeminiSearchScreen(
    geminiSearchState: GeminiSearchState,
    connectivityStatus: StateFlow<ConnectivityStatus>,
    onGeminiSearchEvent: (GeminiSearchEvent) -> Unit
) {

    val isAlreadyStartConversation = geminiSearchState.isAlreadyStartConversation
    val geminiChatHistoryResponse = geminiSearchState.chatHistoryResponse
    val geminiTypingResponse = geminiSearchState.typingResponse
    val isGeminiTyping = geminiSearchState.isGeminiTyping
    val searchInput = geminiSearchState.searchInput

    val connectivityState by connectivityStatus.collectAsStateWithLifecycle()

    var isSearchTextFieldFocused by rememberSaveable {
        mutableStateOf(false)
    }

    val isKeyboardOpen by isKeyboardOpen()

    val focusManager = LocalFocusManager.current

    val customTextModifier = Modifier.padding(
        start = dimensionResource(id = R.dimen._7dp),
        top = dimensionResource(id = R.dimen._5dp),
        end = dimensionResource(id = R.dimen._5dp),
        bottom = dimensionResource(id = R.dimen._5dp)
    )

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (!isKeyboardOpen && isSearchTextFieldFocused) focusManager.clearFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ContentSurface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(_08f)
                .padding(dimensionResource(id = R.dimen._10dp))
        ) {
            if (!isAlreadyStartConversation) {
                WelcomeWidget(modifier = Modifier.fillMaxSize())
            } else {
                ChatLazyColumn(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen._5dp))
                        .fillMaxWidth(),
                    customTextModifier = customTextModifier,
                    chatHistoryResponse = geminiChatHistoryResponse,
                    typingResponse = geminiTypingResponse,
                    isGeminiTyping = isGeminiTyping
                )
            }
        }
        CustomDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen._10dp)),
            thickness = dimensionResource(id = R.dimen._3dp)
        )
        ContentSurface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(_02f)
                .padding(dimensionResource(id = R.dimen._10dp))
        ) {
            Column {
                SearchTextField(
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen._10dp),
                            top = dimensionResource(id = R.dimen._10dp),
                            end = dimensionResource(id = R.dimen._10dp),
                            bottom = dimensionResource(id = R.dimen._2dp)
                        )
                        .fillMaxWidth()
                        .weight(_08f)
                        .onFocusChanged { focusState ->
                            isSearchTextFieldFocused = focusState.isFocused
                        },
                    searchText = searchInput,
                    onSearchTextChange = {
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchInputUpdate(it))
                    },
                    textFieldEnabled = !isGeminiTyping && connectivityState is ConnectivityStatus.Available,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontSize = _18sp,
                        fontWeight = FontWeight.Bold
                    ),
                    placeHolderTextStyle = MaterialTheme.typography.labelSmall.copy(
                        fontSize = _16sp,
                        fontWeight = FontWeight.Bold
                    ),
                    trailingIconEnabled = searchInput.isNotBlank(),
                    onSearchTrailingIconClick = {
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchRequest)
                    },
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen._25dp)),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.tertiary,
                        focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                        focusedPlaceholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = _05f),
                        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onTertiary.copy(alpha = _05f),
                        focusedTrailingIconColor = MaterialTheme.colorScheme.tertiaryContainer,
                        unfocusedTrailingIconColor = MaterialTheme.colorScheme.tertiaryContainer,
                    )
                )
                ConnectionInfoWidget(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = dimensionResource(id = R.dimen._15dp),
                            end = dimensionResource(id = R.dimen._15dp),
                            bottom = dimensionResource(id = R.dimen._5dp)
                        ),
                    connectivityStatus = connectivityState
                )
            }
        }
    }
}

//@PreviewAnnotation
//@Composable
//fun GeminiSearchScreenPreview() {
//    GeminiHelperTheme {
//        GeminiSearchScreen(
//            geminiSearchState = GeminiSearchState(),
//            connectivityStatus = MutableStateFlow(ConnectivityStatus.Available(ConnectivityType.Wi_Fi))
//        ) {
//
//        }
//    }
//}



@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
annotation class PreviewAnnotation