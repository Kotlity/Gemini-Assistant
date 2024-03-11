package com.gemini.assistant.presentation.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.gemini.assistant.utils.helpers.TopAppBarTitleProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier,
    topAppBarTitleProvider: TopAppBarTitleProvider,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    scrolledContainerColor: Color = MaterialTheme.colorScheme.outline,
    scrollBehavior: TopAppBarScrollBehavior?,
    onNavigationIconClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            CustomTopAppBarTitle(topAppBarTitleProvider = topAppBarTitleProvider)
        },
        navigationIcon = {
            CustomTopAppBarNavigationIcon(onIconClick = onNavigationIconClick)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            scrolledContainerColor = scrolledContainerColor,
            navigationIconContentColor = contentColor,
            titleContentColor = contentColor
        ),
        scrollBehavior = scrollBehavior
    )
}