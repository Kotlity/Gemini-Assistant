package com.gemini.assistant.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gemini.assistant.R
import com.gemini.assistant.presentation.theme.GeminiHelperTheme
import com.gemini.assistant.utils.helpers.isKeyboardOpen
import kotlinx.coroutines.delay

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    textFieldEnabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    placeHolderText: String = stringResource(id = R.string.searchTextFieldPlaceholder),
    placeHolderTextStyle: TextStyle = LocalTextStyle.current,
    trailingIconEnabled: Boolean = false,
    onSearchTrailingIconClick: () -> Unit,
    shape: Shape = OutlinedTextFieldDefaults.shape,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
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
                style = placeHolderTextStyle
            )
        },
        trailingIcon = {
            SearchTrailingIcon(
                enabled = trailingIconEnabled,
                onSearchTrailingIconClick = onSearchTrailingIconClick
            )
        },
        shape = shape,
        colors = colors
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