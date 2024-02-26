package com.gemini.assistant.presentation.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.flowWithLifecycle
import com.gemini.assistant.R
import com.gemini.assistant.domain.model.ChatSearchModel
import com.gemini.assistant.presentation.composables.ChatFloatingActionButton
import com.gemini.assistant.presentation.composables.ChatResponseLazyColumn
import com.gemini.assistant.presentation.composables.ChatSearchHistoryLazyColumn
import com.gemini.assistant.presentation.composables.ConnectionInfoWidget
import com.gemini.assistant.presentation.composables.ContentSurface
import com.gemini.assistant.presentation.composables.CustomDivider
import com.gemini.assistant.presentation.composables.SearchTextField
import com.gemini.assistant.presentation.composables.WelcomeWidget
import com.gemini.assistant.presentation.events.GeminiSearchEvent
import com.gemini.assistant.presentation.states.GeminiChatSearchState
import com.gemini.assistant.utils.Constants.MAX_SEARCH_QUERIES
import com.gemini.assistant.utils.Constants._02f
import com.gemini.assistant.utils.Constants._05f
import com.gemini.assistant.utils.Constants._08f
import com.gemini.assistant.utils.Constants._100L
import com.gemini.assistant.utils.Constants._16sp
import com.gemini.assistant.utils.Constants._18sp
import com.gemini.assistant.utils.Constants._500
import com.gemini.assistant.utils.Constants._300L
import com.gemini.assistant.utils.helpers.animateScrollToEnd
import com.gemini.assistant.utils.helpers.isKeyboardOpen
import com.gemini.assistant.utils.helpers.scrollToEnd
import com.gemini.assistant.utils.internet_connection.ConnectivityStatus
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@Composable
fun GeminiChatSearchScreen(
    geminiChatSearchState: GeminiChatSearchState,
    connectivityStatus: ConnectivityStatus,
    chatSearchHistory: List<ChatSearchModel>,
    isShowScrollDownButton: Boolean,
    onGeminiSearchEvent: (GeminiSearchEvent) -> Unit
) {

    val isAlreadyStartConversation = geminiChatSearchState.isAlreadyStartConversation
    val geminiChatHistoryResponse = geminiChatSearchState.chatHistoryResponse
    val geminiTypingResponse = geminiChatSearchState.typingResponse
    val isGeminiTyping = geminiChatSearchState.isGeminiTyping
    val textFieldSearchInput = geminiChatSearchState.chatSearchInputState.textFieldSearchInput
    val textSearchInput = geminiChatSearchState.chatSearchInputState.textSearchInput

    var isSearchTextFieldFocused by rememberSaveable {
        mutableStateOf(false)
    }

    val isKeyboardOpen by isKeyboardOpen()

    val focusManager = LocalFocusManager.current

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val clipboardManager = LocalClipboardManager.current

    val lazyListState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    var scaffoldHeight by rememberSaveable {
        mutableIntStateOf(0)
    }

    val customTextModifier = Modifier.padding(
        start = dimensionResource(id = R.dimen._7dp),
        top = dimensionResource(id = R.dimen._5dp),
        end = dimensionResource(id = R.dimen._5dp),
        bottom = dimensionResource(id = R.dimen._5dp)
    )

    LaunchedEffect(key1 = isKeyboardOpen) {
        if (!isKeyboardOpen && isSearchTextFieldFocused) focusManager.clearFocus()
    }

    LaunchedEffect(key1 = lazyListState.canScrollForward) {
        val isFloatingActionButtonShow = lazyListState.canScrollForward && !isGeminiTyping
        snapshotFlow { isFloatingActionButtonShow }
            .distinctUntilChanged()
            .debounce(_300L)
            .flowWithLifecycle(lifecycle)
            .collectLatest { canScrollForward ->
                if (canScrollForward) onGeminiSearchEvent(GeminiSearchEvent.IsShowScrollDownButtonUpdate(true))
                else onGeminiSearchEvent(GeminiSearchEvent.IsShowScrollDownButtonUpdate(false))
            }
    }

    LaunchedEffect(key1 = geminiTypingResponse) {
        val isShouldAutomaticallyScrollToEnd = isGeminiTyping && geminiTypingResponse.isNotBlank()
        snapshotFlow { isShouldAutomaticallyScrollToEnd }
            .debounce(_100L)
            .collectLatest { isShouldScrollToEnd ->
                if (isShouldScrollToEnd) lazyListState.scrollToEnd(scaffoldHeight)
            }
    }

    LaunchedEffect(key1 = chatSearchHistory) {
        val isChatSearchHistoryFull = chatSearchHistory.size == MAX_SEARCH_QUERIES
        if (isChatSearchHistoryFull) onGeminiSearchEvent(GeminiSearchEvent.OnDeleteChatSearchOlder)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ){
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .weight(_08f)
                .padding(dimensionResource(id = R.dimen._10dp))
                .onGloballyPositioned {
                    scaffoldHeight = it.size.height
                },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isShowScrollDownButton,
                    enter = slideInVertically(animationSpec = tween(durationMillis = _500), initialOffsetY = { lazyListState.layoutInfo.viewportEndOffset }),
                    exit = slideOutVertically(animationSpec = tween(durationMillis = _500), targetOffsetY = { lazyListState.layoutInfo.viewportEndOffset })
                ) {
                    ChatFloatingActionButton(
                        onClick = {
                            coroutineScope.launch {
                                lazyListState.animateScrollToEnd(scaffoldHeight)
                                onGeminiSearchEvent(GeminiSearchEvent.IsShowScrollDownButtonUpdate(false))
                            }
                        }
                    )
                }
            }
        ) { paddingValues ->
            ContentSurface(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                if (!isAlreadyStartConversation) {
                    WelcomeWidget(modifier = Modifier.fillMaxSize())
                } else {
                    ChatResponseLazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        lazyListState = lazyListState,
                        customTextModifier = customTextModifier,
                        textSearchInput = textSearchInput,
                        chatHistoryResponse = geminiChatHistoryResponse,
                        typingResponse = geminiTypingResponse,
                        isGeminiTyping = isGeminiTyping,
                        onTextCopied = { copiedText ->
                            val annotatedString = AnnotatedString(copiedText)
                            clipboardManager.setText(annotatedString)
                        }
                    )
                }
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
                ChatSearchHistoryLazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    chatSearchHistory = chatSearchHistory,
                    onItemClick = { searchHistoryItem ->
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchInputUpdate(searchHistoryItem))
                    }
                )
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
                    searchText = textFieldSearchInput,
                    onSearchTextChange = {
                        onGeminiSearchEvent(GeminiSearchEvent.OnSearchInputUpdate(it))
                    },
                    textFieldEnabled = !isGeminiTyping && connectivityStatus is ConnectivityStatus.Available,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontSize = _18sp,
                        fontWeight = FontWeight.Bold
                    ),
                    placeHolderTextStyle = MaterialTheme.typography.labelSmall.copy(
                        fontSize = _16sp,
                        fontWeight = FontWeight.Bold
                    ),
                    trailingIconEnabled = textFieldSearchInput.isNotBlank(),
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
                    connectivityStatus = connectivityStatus
                )
            }
        }
    }

}

//@PreviewAnnotation
//@Composable
//fun GeminiSearchScreenPreview() {
//    GeminiHelperTheme {
//        GeminiChatSearchScreen(
//            geminiChatSearchState = GeminiChatSearchState(),
//            connectivityStatus = ConnectivityStatus.Available(ConnectivityType.Wi_Fi),
//            chatSearchHistory = listOf(
//                ChatSearchModel("hello, how are you ?"),
//                ChatSearchModel("можешь рассказать какую-нибудь длинную историю, пожалуйста ?"),
//                ChatSearchModel("ываываываываываываываываываываываываываываыавываываываываываываываываыва"),
//                ChatSearchModel("проверка проверка проверка проверка проверка"),
//                ChatSearchModel("тест тест тест тест тест")
//            ),
//            isShowScrollDownButton = true
//        ) {
//
//        }
//    }
//}



@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, showSystemUi = true)
annotation class PreviewAnnotation