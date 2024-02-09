package com.gemini.assistant.presentation.composables

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gemini.assistant.R
import com.gemini.assistant.presentation.theme.GeminiHelperTheme
import com.gemini.assistant.utils.Constants._05f
import kotlinx.coroutines.delay

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    textFieldEnabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    placeHolderText: String = stringResource(id = R.string.searchTextFieldPlaceholder),
    placeHolderTextAlpha: Float = _05f,
    trailingIconEnabled: Boolean = false,
    onSearchTrailingIconClick: () -> Unit
) {

    OutlinedTextField(
        modifier = modifier,
        value = searchText,
        onValueChange = onSearchTextChange,
        enabled = textFieldEnabled,
        textStyle = textStyle,
        placeholder = {
            Text(
                text = placeHolderText,
                color = LocalTextStyle.current.color.copy(alpha = placeHolderTextAlpha)
            )
        },
        trailingIcon = {
            SearchTrailingIcon(
                enabled = trailingIconEnabled,
                onSearchTrailingIconClick = onSearchTrailingIconClick
            )
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestPreview() {
    GeminiHelperTheme {

        var searchText by rememberSaveable {
            mutableStateOf("")
        }

        var searchTextFieldEnabled by rememberSaveable {
            mutableStateOf(true)
        }

        var isSearchTextFieldFocused by rememberSaveable {
            mutableStateOf(false)
        }

        val isKeyboardOpen by isKeyboardOpen()

        val focusManager = LocalFocusManager.current

        LaunchedEffect(key1 = searchTextFieldEnabled) {
            if (!searchTextFieldEnabled) {
                delay(5000)
                searchTextFieldEnabled = true
            }
        }

        LaunchedEffect(key1 = isKeyboardOpen) {
            if (!isKeyboardOpen && isSearchTextFieldFocused) {
                focusManager.clearFocus()
            }
        }

        SearchTextField(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(0.8f)
                .onFocusChanged { focusState ->
                    isSearchTextFieldFocused = focusState.isFocused
                },
            searchText = searchText,
            onSearchTextChange = { searchText = it },
            textFieldEnabled = searchTextFieldEnabled,
            trailingIconEnabled = searchText.isNotBlank(),
            onSearchTrailingIconClick = {
                searchText = ""
                searchTextFieldEnabled = false
            }
        )
    }
}

@Composable
private fun isKeyboardOpen(): State<Boolean> {
    val keyboardState = remember { mutableStateOf(false) }
    val view = LocalView.current

    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            keyboardState.value = if (keypadHeight > screenHeight * 0.15) true
            else false

        }

        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}